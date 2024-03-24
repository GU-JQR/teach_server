package com.ruoyi.teach.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.domain.vo.ClassTreeVO;

/**
 * 学员分期Service接口
 *
 * @author sqc
 * @date 2024-03-05
 */
public interface ISysClassService
{
    /**
     * 查询学员分期
     *
     * @param id 学员分期主键
     * @return 学员分期
     */
    public SysClass selectSysClassById(Long id);

    /**
     * 查询学员分期列表
     *
     * @param sysClass 学员分期
     * @return 学员分期集合
     */
    public List<SysClass> selectSysClassList(SysClass sysClass);

    /**
     * 新增学员分期
     *
     * @param sysClass 学员分期
     * @return 结果
     */
    public int insertSysClass(SysClass sysClass);

    /**
     * 修改学员分期
     *
     * @param sysClass 学员分期
     * @return 结果
     */
    public int updateSysClass(SysClass sysClass);

    /**
     * 根据期数deptId注销学员
     * @param sysClass
     * @return
     */
    public int cancelStudentByDeptId(SysClass sysClass);

    /**
     * 批量删除学员分期
     *
     * @param ids 需要删除的学员分期主键集合
     * @return 结果
     */
    public int deleteSysClassByIds(Long[] ids);

    /**
     * 删除学员分期信息
     *
     * @param id 学员分期主键
     * @return 结果
     */
    public int deleteSysClassById(Long id);

    /**
     * 查询期数树结构信息
     *
     * @param sysClass 期数信息
     * @return 期数树信息集合
     */
    public List<ClassTreeVO> selectClassTreeList(SysClass sysClass);

    SysClass selectNowClassId();

    AjaxResult selectChartInfoById(Long id );
}
