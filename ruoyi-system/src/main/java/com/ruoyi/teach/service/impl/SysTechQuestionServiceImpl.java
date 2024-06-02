package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysTechQuestionMapper;
import com.ruoyi.system.domain.SysTechQuestion;
import com.ruoyi.system.service.ISysTechQuestionService;

/**
 * 评学题库信息Service业务层处理
 * 
 * @author sqc
 * @date 2024-04-05
 */
@Service
public class SysTechQuestionServiceImpl implements ISysTechQuestionService 
{
    @Autowired
    private SysTechQuestionMapper sysTechQuestionMapper;

    /**
     * 查询评学题库信息
     * 
     * @param id 评学题库信息主键
     * @return 评学题库信息
     */
    @Override
    public SysTechQuestion selectSysTechQuestionById(Long id)
    {
        return sysTechQuestionMapper.selectSysTechQuestionById(id);
    }

    /**
     * 查询评学题库信息列表
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 评学题库信息
     */
    @Override
    public List<SysTechQuestion> selectSysTechQuestionList(SysTechQuestion sysTechQuestion)
    {
        return sysTechQuestionMapper.selectSysTechQuestionList(sysTechQuestion);
    }

    /**
     * 新增评学题库信息
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 结果
     */
    @Override
    public int insertSysTechQuestion(SysTechQuestion sysTechQuestion)
    {
        sysTechQuestion.setCreateTime(DateUtils.getNowDate());
        return sysTechQuestionMapper.insertSysTechQuestion(sysTechQuestion);
    }

    /**
     * 修改评学题库信息
     * 
     * @param sysTechQuestion 评学题库信息
     * @return 结果
     */
    @Override
    public int updateSysTechQuestion(SysTechQuestion sysTechQuestion)
    {
        sysTechQuestion.setUpdateTime(DateUtils.getNowDate());
        return sysTechQuestionMapper.updateSysTechQuestion(sysTechQuestion);
    }

    /**
     * 批量删除评学题库信息
     * 
     * @param ids 需要删除的评学题库信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTechQuestionByIds(Long[] ids)
    {
        return sysTechQuestionMapper.deleteSysTechQuestionByIds(ids);
    }

    /**
     * 删除评学题库信息信息
     * 
     * @param id 评学题库信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTechQuestionById(Long id)
    {
        return sysTechQuestionMapper.deleteSysTechQuestionById(id);
    }
}
