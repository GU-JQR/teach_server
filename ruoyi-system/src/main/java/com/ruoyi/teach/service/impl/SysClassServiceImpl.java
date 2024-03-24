package com.ruoyi.teach.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.domain.vo.ClassTreeVO;
import com.ruoyi.teach.mapper.SysStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysClassMapper;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.service.ISysClassService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 学员分期Service业务层处理
 *
 * @author sqc
 * @date 2024-03-05
 */
@Service
public class SysClassServiceImpl implements ISysClassService
{
    @Autowired
    private SysClassMapper sysClassMapper;

    @Autowired
    private SysStudentMapper sysStudentMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询学员分期
     *
     * @param id 学员分期主键
     * @return 学员分期
     */
    @Override
    public SysClass selectSysClassById(Long id)
    {
        return sysClassMapper.selectSysClassById(id);
    }

    /**
     * 查询学员分期列表
     *
     * @param sysClass 学员分期
     * @return 学员分期
     */
    @Override
    public List<SysClass> selectSysClassList(SysClass sysClass)
    {
        return sysClassMapper.selectSysClassList(sysClass);
    }

    /**
     * 新增学员分期
     *
     * @param sysClass 学员分期
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSysClass(SysClass sysClass)
    {
        sysClass.setCreateTime(DateUtils.getNowDate());
        LocalDateTime now = LocalDateTime.now();
        sysClass.setYear(now.getYear());
        //期数编号 队伍编号+年份+期数
        List<SysDictData> dictDatas = sysDictDataMapper.selectDictDataByType("sys_team_name");
        Optional<SysDictData> dictData = dictDatas.stream().filter(k->k.getDictLabel().equals(sysClass.getTeam())).findFirst();
        sysClass.setNumber(dictData.get().getDictValue() + now.getYear()+ String.format("%02d",sysClassMapper.countSysClassByYear(now.getYear())));
        //新建部门
        SysDept dept = new SysDept();
        dept.setParentId(102L);
        dept.setAncestors("0,100,102");
        dept.setDeptName(sysClass.getName());
        int result = sysDeptMapper.insertDept(dept);
        if(result>0){
            sysClass.setDeptId(dept.getDeptId());
            result = sysClassMapper.insertSysClass(sysClass);;
        }
        return result;
    }

    /**
     * 修改学员分期
     *
     * @param sysClass 学员分期
     * @return 结果
     */
    @Override
    public int updateSysClass(SysClass sysClass)
    {
        sysClass.setUpdateTime(DateUtils.getNowDate());
        //期数编号 队伍编号+年份
        LocalDateTime now = LocalDateTime.now();
        sysClass.setYear(now.getYear());
        List<SysDictData> dictDatas = sysDictDataMapper.selectDictDataByType("sys_team_name");
        Optional<SysDictData> dictData = dictDatas.stream().filter(k->k.getDictLabel().equals(sysClass.getTeam())).findFirst();
        sysClass.setNumber(dictData.get().getDictValue() + now.getYear() + String.format("%02d",sysClassMapper.countSysClassByYear(now.getYear())));
        return sysClassMapper.updateSysClass(sysClass);
    }

    /**
     * 根据期数deptId注销学员
     * @param sysClass
     * @return
     */
    public int cancelStudentByDeptId(SysClass sysClass){
        sysClassMapper.updateSysClass(sysClass);
        return sysClassMapper.cancelStudentByDeptId(sysClass.getDeptId());
    }

    /**
     * 批量删除学员分期
     *
     * @param ids 需要删除的学员分期主键
     * @return 结果
     */
    @Override
    public int deleteSysClassByIds(Long[] ids)
    {
        return sysClassMapper.deleteSysClassByIds(ids);
    }

    /**
     * 删除学员分期信息
     *
     * @param id 学员分期主键
     * @return 结果
     */
    @Override
    public int deleteSysClassById(Long id)
    {
        return sysClassMapper.deleteSysClassById(id);
    }

    /**
     * 查询期数树结构信息
     *
     * @param sysClass 期数信息
     * @return 期数树信息集合
     */
    @Override
    public List<ClassTreeVO> selectClassTreeList(SysClass sysClass){
        List<ClassTreeVO> list = new ArrayList<>();
        List<SysClass> classes = sysClassMapper.selectSysClassList(sysClass);
        if(StringUtils.isNotNull(classes)){
            Random random = new Random();
            List<String> teams = classes.stream().map(k->k.getTeam()).distinct().collect(Collectors.toList());
            for (String team:teams) {
                List<SysClass> classe_temp = classes.stream().filter(v->v.getTeam().equals(team)).collect(Collectors.toList());
                ClassTreeVO treeVO = new ClassTreeVO(random.nextLong(),team);
                treeVO.getChildren().add(treeByYear(classe_temp));
                list.add(treeVO);
            }
        }

        return list;
    }

    public ClassTreeVO treeByYear(List<SysClass> classes){
        Map<Integer,List<ClassTreeVO>> map = new HashMap<>();
        Random random = new Random();
        ClassTreeVO top = new ClassTreeVO(random.nextLong(),"年份");
        for (SysClass item:classes) {
            if(!StringUtils.isNotNull(map.get(item.getYear()))){
                map.put(item.getYear(),new ArrayList<>());
            }
            map.get(item.getYear()).add(new ClassTreeVO(item.getId(),item.getName()));
        }
        for (Integer key : map.keySet()) { //遍历key
            ClassTreeVO node = new ClassTreeVO(random.nextLong(),key.toString());
            node.setChildren(map.get(key));
            top.getChildren().add(node);
        }

        return top;
    }

    @Override
    public SysClass selectNowClassId() {
        SysClass sysClass= sysClassMapper.selectNowClassId();
        return sysClass;
    }

    //构建成绩分析表格
    //数据格式{name:"一期"，info:[个数，百分比]}
    @Override
    public AjaxResult selectChartInfoById(Long id) {
        List<SysClass> sysClassList= sysClassMapper.selectChartInfoById(id);
        List<Long> result = sysClassList.stream().map(SysClass::getId).collect(Collectors.toList());
        Map<String, List> map = new HashMap<>();
        List<String> listName = new ArrayList<>();
        List<Integer> listCount = new ArrayList<>();
        List<Integer> listPer = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            listName.add((i+1)+"期");
        }
        for (Long item:result){
            Optional<SysClass> sysClass = sysClassList.stream().filter(k -> k.getId().toString().equals(String.valueOf(item))).findFirst();
            String name = sysClass.get().getName();
            SysStudent sysStudent = sysStudentMapper.selectSysStudentByClassId(item);
            if(sysStudent.isEmpty()){
                return AjaxResult.error(name+"目前没有学员请前往后台添加");
            }
            listCount.add(sysStudent.getEnhanceCount());
            listPer.add(sysStudent.getEnhanceCount()*100/sysStudent.getClassStuCount());
        }
        map.put("xData",listName);
        map.put("yDataLeft",listCount);
        map.put("yDataRight",listPer);
        return AjaxResult.success(map);
    }
}
