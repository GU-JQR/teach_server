package com.ruoyi.teach.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.mapper.SysClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.teach.domain.SysEducation;
import com.ruoyi.teach.mapper.SysStudentMapper;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.service.ISysStudentService;

/**
 * 学员信息Service业务层处理
 *
 * @author sqc
 * @date 2024-03-03
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService
{
    @Autowired
    private SysStudentMapper sysStudentMapper;

    @Autowired
    private SysClassMapper sysClassMapper;

    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    @Override
    public SysStudent selectSysStudentById(Long id)
    {
        return sysStudentMapper.selectSysStudentById(id);
    }

    /**
     * 查询学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 查询全部学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息集合
     */
    @Override
    public List<SysStudent> selectSysStudentListAll(SysStudent sysStudent){
        return sysStudentMapper.selectSysStudentListAll(sysStudent);
    }

    /**
     * 新增学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysStudent(SysStudent sysStudent)
    {
        sysStudent.setCreateTime(DateUtils.getNowDate());
        int rows = sysStudentMapper.insertSysStudent(sysStudent);
        insertSysEducation(sysStudent);
        return rows;
    }

    /**
     * 修改学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        sysStudent.setUpdateTime(DateUtils.getNowDate());
        sysStudentMapper.deleteSysEducationByStudentId(sysStudent.getId());
        insertSysEducation(sysStudent);
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysStudentByIds(Long[] ids)
    {
        sysStudentMapper.deleteSysEducationByStudentIds(ids);
        return sysStudentMapper.deleteSysStudentByIds(ids);
    }

    /**
     * 删除学员信息信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysStudentById(Long id)
    {
        sysStudentMapper.deleteSysEducationByStudentId(id);
        return sysStudentMapper.deleteSysStudentById(id);
    }

    @Override
    public AjaxResult selectSysStudentByClassId(Long classId) {
        //查询优良信息
        SysStudent sysStudent= sysStudentMapper.selectSysStudentByClassId(classId);
        //查询当前期数学生的情况
        //将信息封装成map
        AjaxResult ajax = AjaxResult.success();
        HashMap<String, Integer> mapNow = new HashMap<>();
        ajax.put("nowClass",handle(sysStudent));
        //根据classId查找number
        SysClass sysClass = sysClassMapper.selectSysClassById(classId);
        SysClass sysClassLast = sysClassMapper.selectLastSysClassByNumber(Long.valueOf(sysClass.getNumber()));
        if(sysClassLast == null){
            ajax.put("lastClass",null);
            return ajax;
        }
        SysStudent sysStudentLast = sysStudentMapper.selectSysStudentByClassId(sysClassLast.getId());
        if(sysClassLast == null){
            ajax.put("lastClass",null);
            return ajax;
        }
        ajax.put("lastClass",handle(sysStudentLast));
        return ajax;
    }
    private Map<String,Integer> handle(SysStudent sysStudent){
        HashMap<String, Integer> mapNow = new HashMap<>();
        mapNow.put("level3",sysStudent.getLevel3());
        mapNow.put("level2",sysStudent.getLevel2());
        mapNow.put("level1",sysStudent.getLevel1());
        mapNow.put("level0",sysStudent.getLevel0());
        mapNow.put("enhanceCount",sysStudent.getEnhanceCount());
        mapNow.put("classStuCount",sysStudent.getClassStuCount());
        return mapNow;
    }
    /**
     * 新增教育经历信息信息
     *
     * @param sysStudent 学员信息对象
     */
    public void insertSysEducation(SysStudent sysStudent)
    {
        List<SysEducation> sysEducationList = sysStudent.getSysEducationList();
        Long id = sysStudent.getId();
        if (StringUtils.isNotNull(sysEducationList))
        {
            List<SysEducation> list = new ArrayList<SysEducation>();
            for (SysEducation sysEducation : sysEducationList)
            {
                sysEducation.setStudentId(id);
                list.add(sysEducation);
            }
            if (list.size() > 0)
            {
                sysStudentMapper.batchSysEducation(list);
            }
        }
    }
}
