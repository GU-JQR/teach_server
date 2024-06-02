package com.ruoyi.teach.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审课记录对象 sys_review
 * 
 * @author ruoyi
 * @date 2024-03-09
 */
public class SysReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 科目名称 */
    @Excel(name = "科目名称")
    private String subject;

    /** 内容 */
    @Excel(name = "内容")
    private String topic;

    /** 授课教员 */
    @Excel(name = "授课教员")
    private String instructor;

    /** 授课地点 */
    @Excel(name = "授课地点")
    private String area;

    /** 建议 */
    @Excel(name = "建议")
    private String suggestion;

    /** 审课结果 */
    @Excel(name = "审课结果")
    private String result;

    /** 审批组长 */
    @Excel(name = "审批组长")
    private String reviewLeader;



    @Excel(name = "删除标记")
    private String delFlag;

    /** 审评时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审评时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setTopic(String topic) 
    {
        this.topic = topic;
    }

    public String getTopic() 
    {
        return topic;
    }
    public void setInstructor(String instructor) 
    {
        this.instructor = instructor;
    }

    public String getInstructor() 
    {
        return instructor;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setSuggestion(String suggestion) 
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion() 
    {
        return suggestion;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setReviewLeader(String reviewLeader) 
    {
        this.reviewLeader = reviewLeader;
    }

    public String getReviewLeader() 
    {
        return reviewLeader;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("subject", getSubject())
            .append("topic", getTopic())
            .append("instructor", getInstructor())
            .append("area", getArea())
            .append("suggestion", getSuggestion())
            .append("result", getResult())
            .append("delFlag", getDelFlag())
            .append("reviewLeader", getReviewLeader())
            .append("reviewTime", getReviewTime())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
