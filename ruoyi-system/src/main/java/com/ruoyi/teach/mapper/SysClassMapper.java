package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.teach.domain.SysClass;

/**
 * 学员分期Mapper接口
 *
 * @author sqc
 * @date 2024-03-05
 */
public interface SysClassMapper
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
     * 删除学员分期
     *
     * @param id 学员分期主键
     * @return 结果
     */
    public int deleteSysClassById(Long id);

    /**
     * 批量删除学员分期
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysClassByIds(Long[] ids);
    //查询上一期的id
    SysClass selectLastSysClassByNumber(Long number);

    //查询最新的id
    SysClass selectNowClassId();

    List<SysClass> selectChartInfoById(Long id);
}
