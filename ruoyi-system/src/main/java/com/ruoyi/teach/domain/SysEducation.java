package com.ruoyi.teach.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教育经历信息对象 sys_education
 * 
 * @author sqc
 * @date 2024-03-03
 */
public class SysEducation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 学员ID */
    @Excel(name = "学员ID")
    private Long studentId;

    /** 上学时间 */
    @Excel(name = "上学时间")
    private String eduPeriod;

    /** 学校（院） */
    @Excel(name = "学校", readConverterExp = "院=")
    private String college;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 学历 */
    @Excel(name = "学历")
    private String edu;

    /** 主修课程 */
    @Excel(name = "主修课程")
    private String course;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setEduPeriod(String eduPeriod) 
    {
        this.eduPeriod = eduPeriod;
    }

    public String getEduPeriod() 
    {
        return eduPeriod;
    }
    public void setCollege(String college) 
    {
        this.college = college;
    }

    public String getCollege() 
    {
        return college;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setEdu(String edu) 
    {
        this.edu = edu;
    }

    public String getEdu() 
    {
        return edu;
    }
    public void setCourse(String course) 
    {
        this.course = course;
    }

    public String getCourse() 
    {
        return course;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentId", getStudentId())
            .append("eduPeriod", getEduPeriod())
            .append("college", getCollege())
            .append("major", getMajor())
            .append("edu", getEdu())
            .append("course", getCourse())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
