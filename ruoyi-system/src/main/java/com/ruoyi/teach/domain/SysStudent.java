package com.ruoyi.teach.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.teach.domain.SysEducation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学员信息对象 sys_student
 * 
 * @author sqc
 * @date 2024-03-03
 */
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学员ID */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 学历 */
    @Excel(name = "学历")
    private String edu;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 班级 */
    @Excel(name = "班级")
    private Long classId;

    /** 班级名称 */
    private String className;

    /** 计算机能力 */
    private String capacityComputer;

    /** 语言能力 */
    private String capacityLanguage;

    /** 专业能力 */
    private String capacityMajor;

    /** 个人评价 */
    private String evaluatePerson;

    /** 工作态度 */
    private String evaluateWork;

    /** 教育经历信息信息 */
    private List<SysEducation> sysEducationList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBirth(Date birth) 
    {
        this.birth = birth;
    }

    public Date getBirth() 
    {
        return birth;
    }
    public void setAge(Integer age) 
    {
        this.age = age;
    }

    public Integer getAge() 
    {
        return age;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEdu(String edu) 
    {
        this.edu = edu;
    }

    public String getEdu() 
    {
        return edu;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCapacityComputer(String capacityComputer)
    {
        this.capacityComputer = capacityComputer;
    }

    public String getCapacityComputer() 
    {
        return capacityComputer;
    }
    public void setCapacityLanguage(String capacityLanguage) 
    {
        this.capacityLanguage = capacityLanguage;
    }

    public String getCapacityLanguage() 
    {
        return capacityLanguage;
    }
    public void setCapacityMajor(String capacityMajor) 
    {
        this.capacityMajor = capacityMajor;
    }

    public String getCapacityMajor() 
    {
        return capacityMajor;
    }
    public void setEvaluatePerson(String evaluatePerson) 
    {
        this.evaluatePerson = evaluatePerson;
    }

    public String getEvaluatePerson() 
    {
        return evaluatePerson;
    }
    public void setEvaluateWork(String evaluateWork) 
    {
        this.evaluateWork = evaluateWork;
    }

    public String getEvaluateWork() 
    {
        return evaluateWork;
    }

    public List<SysEducation> getSysEducationList()
    {
        return sysEducationList;
    }

    public void setSysEducationList(List<SysEducation> sysEducationList)
    {
        this.sysEducationList = sysEducationList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("birth", getBirth())
            .append("age", getAge())
            .append("phone", getPhone())
            .append("edu", getEdu())
            .append("address", getAddress())
            .append("email", getEmail())
            .append("major", getMajor())
            .append("avatar", getAvatar())
            .append("classId", getClassId())
            .append("capacityComputer", getCapacityComputer())
            .append("capacityLanguage", getCapacityLanguage())
            .append("capacityMajor", getCapacityMajor())
            .append("evaluatePerson", getEvaluatePerson())
            .append("evaluateWork", getEvaluateWork())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("sysEducationList", getSysEducationList())
            .toString();
    }
}
