package com.ruoyi.teach.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.domain.vo.ClassTreeVO;
import com.ruoyi.teach.mapper.SysStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysClassMapper;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.service.ISysClassService;

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
    public int insertSysClass(SysClass sysClass)
    {
        sysClass.setCreateTime(DateUtils.getNowDate());
        sysClass.setYear(sysClass.getNumber()/10000);
        return sysClassMapper.insertSysClass(sysClass);
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
        return sysClassMapper.updateSysClass(sysClass);
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
        List<SysClass> classes = sysClassMapper.selectSysClassList(sysClass);
        Map<Integer,List<ClassTreeVO>> map = new HashMap<>();
        List<ClassTreeVO> list = new ArrayList<>();
        ClassTreeVO top = new ClassTreeVO(0L,"年份");
        for (SysClass item:classes) {
            if(!StringUtils.isNotNull(map.get(item.getYear()))){
                map.put(item.getYear(),new ArrayList<>());
            }
            map.get(item.getYear()).add(new ClassTreeVO(item.getId(),item.getName()));
        }
        Long id = 0L;
        for (Integer key : map.keySet()) { //遍历key
            id = id - 1;
            ClassTreeVO node = new ClassTreeVO(id,key.toString());
            node.setChildren(map.get(key));
            top.getChildren().add(node);
        }
        list.add(top);
        return list;
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
        List<Map> resultList = new LinkedList<>();
        Map<String, List> map = new HashMap<>();
        List<String> listName = new ArrayList<>();
        List<Integer> listCount = new ArrayList<>();
        List<Integer> listPer = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            listName.add((i+1)+"期");
        }
        for (Long item:result){
            SysStudent sysStudent = sysStudentMapper.selectSysStudentByClassId(item);
            listCount.add(sysStudent.getEnhanceCount());
            listPer.add(sysStudent.getEnhanceCount()*100/sysStudent.getClassStuCount());
        }
        map.put("xData",listName);
        map.put("yDataLeft",listCount);
        map.put("yDataRight",listPer);
       return AjaxResult.success(map);
    }
}
