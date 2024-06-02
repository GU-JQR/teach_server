package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.teach.domain.SysQuestion;

/**
 * 评教题库信息Mapper接口
 * 
 * @author sqc
 * @date 2024-03-09
 */
public interface SysQuestionMapper 
{
    /**
     * 查询评教题库信息
     * 
     * @param id 评教题库信息主键
     * @return 评教题库信息
     */
    public SysQuestion selectSysQuestionById(Long id);

    /**
     * 查询评教题库信息列表
     * 
     * @param sysQuestion 评教题库信息
     * @return 评教题库信息集合
     */
    public List<SysQuestion> selectSysQuestionList(SysQuestion sysQuestion);

    /**
     * 查询评教题库信息列表
     *
     * @param classId
     * @return 评教题库信息集合
     */
    public List<SysQuestion> selectSysQuestionListByClassId(Long classId);

    /**
     * 新增评教题库信息
     * 
     * @param sysQuestion 评教题库信息
     * @return 结果
     */
    public int insertSysQuestion(SysQuestion sysQuestion);

    /**
     * 修改评教题库信息
     * 
     * @param sysQuestion 评教题库信息
     * @return 结果
     */
    public int updateSysQuestion(SysQuestion sysQuestion);

    /**
     * 删除评教题库信息
     * 
     * @param id 评教题库信息主键
     * @return 结果
     */
    public int deleteSysQuestionById(Long id);

    /**
     * 批量删除评教题库信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysQuestionByIds(Long[] ids);
}
