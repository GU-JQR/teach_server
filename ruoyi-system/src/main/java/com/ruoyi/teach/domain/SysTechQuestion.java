package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评学题库信息对象 sys_tech_question
 * 
 * @author sqc
 * @date 2024-04-05
 */
public class SysTechQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 期数ID */
    private Long classId;

    /** 期数名称 */
    @Excel(name = "期数名称")
    private String className;

    /** 题目 */
    @Excel(name = "题目")
    private String question;

    /** 选项一 */
    @Excel(name = "选项一")
    private String optionOne;

    /** 选项一分数 */
    @Excel(name = "选项一分数")
    private Integer scoreOne;

    /** 选项二 */
    @Excel(name = "选项二")
    private String optionTwo;

    /** 选项二分数 */
    @Excel(name = "选项二分数")
    private Integer scoreTwo;

    /** 选项三 */
    @Excel(name = "选项三")
    private String optionThree;

    /** 选项三分数 */
    @Excel(name = "选项三分数")
    private Integer scoreThree;

    /** 选项四 */
    @Excel(name = "选项四")
    private String optionFour;

    /** 选项四分数 */
    @Excel(name = "选项四分数")
    private Integer scoreFour;

    /** 删除标志 */
    private String delFlag;

    /** 题目类型 */
    @Excel(name = "题目类型")
    private String questionType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public String getQuestion() 
    {
        return question;
    }
    public void setOptionOne(String optionOne) 
    {
        this.optionOne = optionOne;
    }

    public String getOptionOne() 
    {
        return optionOne;
    }
    public void setScoreOne(Integer scoreOne) 
    {
        this.scoreOne = scoreOne;
    }

    public Integer getScoreOne() 
    {
        return scoreOne;
    }
    public void setOptionTwo(String optionTwo) 
    {
        this.optionTwo = optionTwo;
    }

    public String getOptionTwo() 
    {
        return optionTwo;
    }
    public void setScoreTwo(Integer scoreTwo) 
    {
        this.scoreTwo = scoreTwo;
    }

    public Integer getScoreTwo() 
    {
        return scoreTwo;
    }
    public void setOptionThree(String optionThree) 
    {
        this.optionThree = optionThree;
    }

    public String getOptionThree() 
    {
        return optionThree;
    }
    public void setScoreThree(Integer scoreThree) 
    {
        this.scoreThree = scoreThree;
    }

    public Integer getScoreThree() 
    {
        return scoreThree;
    }
    public void setOptionFour(String optionFour) 
    {
        this.optionFour = optionFour;
    }

    public String getOptionFour() 
    {
        return optionFour;
    }
    public void setScoreFour(Integer scoreFour) 
    {
        this.scoreFour = scoreFour;
    }

    public Integer getScoreFour() 
    {
        return scoreFour;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setQuestionType(String questionType) 
    {
        this.questionType = questionType;
    }

    public String getQuestionType() 
    {
        return questionType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("question", getQuestion())
            .append("optionOne", getOptionOne())
            .append("scoreOne", getScoreOne())
            .append("optionTwo", getOptionTwo())
            .append("scoreTwo", getScoreTwo())
            .append("optionThree", getOptionThree())
            .append("scoreThree", getScoreThree())
            .append("optionFour", getOptionFour())
            .append("scoreFour", getScoreFour())
            .append("delFlag", getDelFlag())
            .append("questionType", getQuestionType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
