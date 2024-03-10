package com.ruoyi.teach.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.teach.domain.SysStudent;

/**
 * 学员信息Service接口
 *
 * @author sqc
 * @date 2024-03-03
 */
public interface ISysStudentService
{
    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    public SysStudent selectSysStudentById(Long id);

    /**
     * 查询学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息集合
     */
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent);

    /**
     * 查询全部学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息集合
     */
    public List<SysStudent> selectSysStudentListAll(SysStudent sysStudent);

    /**
     * 新增学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    public int insertSysStudent(SysStudent sysStudent);

    /**
     * 修改学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    public int updateSysStudent(SysStudent sysStudent);

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员信息主键集合
     * @return 结果
     */
    public int deleteSysStudentByIds(Long[] ids);

    /**
     * 删除学员信息信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    public int deleteSysStudentById(Long id);

    //查询上一期和这一期的学生数据
    AjaxResult selectSysStudentByClassId(Long classId);
}
