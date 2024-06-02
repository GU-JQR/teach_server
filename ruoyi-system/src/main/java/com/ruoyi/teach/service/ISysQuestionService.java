package com.ruoyi.teach.service;

import java.util.List;
import com.ruoyi.teach.domain.SysQuestion;

/**
 * 评教题库信息Service接口
 * 
 * @author sqc
 * @date 2024-03-09
 */
public interface ISysQuestionService 
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
     * 前端查询评教信息列表
     *
     * @param userId 用户Id
     * @param sysQuestion 评教题库信息
     * @return 评教题库信息集合
     */
    public List<SysQuestion> selectSysQuestionListFront(Long userId,SysQuestion sysQuestion);

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
     * 批量删除评教题库信息
     * 
     * @param ids 需要删除的评教题库信息主键集合
     * @return 结果
     */
    public int deleteSysQuestionByIds(Long[] ids);

    /**
     * 删除评教题库信息信息
     * 
     * @param id 评教题库信息主键
     * @return 结果
     */
    public int deleteSysQuestionById(Long id);
}
