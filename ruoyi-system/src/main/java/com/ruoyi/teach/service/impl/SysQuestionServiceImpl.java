package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.mapper.SysStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysQuestionMapper;
import com.ruoyi.teach.domain.SysQuestion;
import com.ruoyi.teach.service.ISysQuestionService;

/**
 * 评教题库信息Service业务层处理
 * 
 * @author sqc
 * @date 2024-03-09
 */
@Service
public class SysQuestionServiceImpl implements ISysQuestionService 
{
    @Autowired
    private SysQuestionMapper sysQuestionMapper;

    @Autowired
    private SysStudentMapper sysStudentMapper;

    /**
     * 查询评教题库信息
     * 
     * @param id 评教题库信息主键
     * @return 评教题库信息
     */
    @Override
    public SysQuestion selectSysQuestionById(Long id)
    {
        return sysQuestionMapper.selectSysQuestionById(id);
    }

    /**
     * 查询评教题库信息列表
     * 
     * @param sysQuestion 评教题库信息
     * @return 评教题库信息
     */
    @Override
    public List<SysQuestion> selectSysQuestionList(SysQuestion sysQuestion)
    {
        return sysQuestionMapper.selectSysQuestionList(sysQuestion);
    }

    /**
     * 前端查询评教信息列表
     *
     * @param userId 用户Id
     * @param sysQuestion 评教题库信息
     * @return 评教题库信息集合
     */
    public List<SysQuestion> selectSysQuestionListFront(Long userId,SysQuestion sysQuestion){
        SysStudent student = sysStudentMapper.selectSysStudentByUserId(userId);
        if(StringUtils.isNotNull(student)){
            sysQuestion.setClassId(student.getClassId());
            return sysQuestionMapper.selectSysQuestionList(sysQuestion);
        }

        return null;
    }

    /**
     * 新增评教题库信息
     * 
     * @param sysQuestion 评教题库信息
     * @return 结果
     */
    @Override
    public int insertSysQuestion(SysQuestion sysQuestion)
    {
        sysQuestion.setCreateTime(DateUtils.getNowDate());
        return sysQuestionMapper.insertSysQuestion(sysQuestion);
    }

    /**
     * 修改评教题库信息
     * 
     * @param sysQuestion 评教题库信息
     * @return 结果
     */
    @Override
    public int updateSysQuestion(SysQuestion sysQuestion)
    {
        sysQuestion.setUpdateTime(DateUtils.getNowDate());
        return sysQuestionMapper.updateSysQuestion(sysQuestion);
    }

    /**
     * 批量删除评教题库信息
     * 
     * @param ids 需要删除的评教题库信息主键
     * @return 结果
     */
    @Override
    public int deleteSysQuestionByIds(Long[] ids)
    {
        return sysQuestionMapper.deleteSysQuestionByIds(ids);
    }

    /**
     * 删除评教题库信息信息
     * 
     * @param id 评教题库信息主键
     * @return 结果
     */
    @Override
    public int deleteSysQuestionById(Long id)
    {
        return sysQuestionMapper.deleteSysQuestionById(id);
    }
}
