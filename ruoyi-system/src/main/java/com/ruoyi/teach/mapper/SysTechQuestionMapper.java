package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTechQuestion;

/**
 * 评学题库信息Mapper接口
 * 
 * @author sqc
 * @date 2024-04-05
 */
public interface SysTechQuestionMapper 
{
    /**
     * 查询评学题库信息
     * 
     * @param id 评学题库信息主键
     * @return 评学题库信息
     */
    public SysTechQuestion selectSysTechQuestionById(Long id);

    /**
     * 查询评学题库信息列表
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 评学题库信息集合
     */
    public List<SysTechQuestion> selectSysTechQuestionList(SysTechQuestion sysTechQuestion);

    /**
     * 查询评学题库信息列表
     *
     * @param classId
     * @return 评学题库信息集合
     */
    public List<SysTechQuestion> selectSysTechQuestionListByClassId(Long classId);

    /**
     * 新增评学题库信息
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 结果
     */
    public int insertSysTechQuestion(SysTechQuestion sysTechQuestion);

    /**
     * 修改评学题库信息
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 结果
     */
    public int updateSysTechQuestion(SysTechQuestion sysTechQuestion);

    /**
     * 删除评学题库信息
     * 
     * @param id 评学题库信息主键
     * @return 结果
     */
    public int deleteSysTechQuestionById(Long id);

    /**
     * 批量删除评学题库信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTechQuestionByIds(Long[] ids);
}
