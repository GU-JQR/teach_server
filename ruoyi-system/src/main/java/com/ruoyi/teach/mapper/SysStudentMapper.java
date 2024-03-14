package com.ruoyi.teach.mapper;

import java.util.List;

import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.domain.SysEducation;

/**
 * 学员信息Mapper接口
 *
 * @author sqc
 * @date 2024-03-03
 */
public interface SysStudentMapper
{
    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    public SysStudent selectSysStudentById(Long id);

    /**
     * 查询学员信息
     *
     * @param userId 学员信息主键
     * @return 学员信息
     */
    public SysStudent selectSysStudentByUserId(Long userId);

    /**
     * 查询学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息集合
     */
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent);

    /**
     * 查询班级学员数量
     *
     * @param classId
     * @return 学员数量
     */
    public int countSysStudentByClassId(Long classId);

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
     * 删除学员信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    public int deleteSysStudentById(Long id);

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysStudentByIds(Long[] ids);

    /**
     * 批量删除教育经历信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysEducationByStudentIds(Long[] ids);

    /**
     * 批量新增教育经历信息
     *
     * @param sysEducationList 教育经历信息列表
     * @return 结果
     */
    public int batchSysEducation(List<SysEducation> sysEducationList);


    /**
     * 通过学员信息主键删除教育经历信息信息
     *
     * @param id 学员信息ID
     * @return 结果
     */
    public int deleteSysEducationByStudentId(Long id);

    //查询classId学生的level和晋升
    SysStudent selectSysStudentByClassId(Long classId);

    //根据学号查询用户
    SysStudent selectSysStudentByNumber(SysStudent sysStudent);
}
