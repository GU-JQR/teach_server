package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
