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

    /** 学号 */
//    @Excel(name = "学号")
    private Long number;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月:yyyy-MM-dd", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;


    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 专业 */
    @Excel(name = "专业")
    private String major;
    /** 学历 */
    @Excel(name = "学历")
    private String edu;
    //提高标志
    @Excel(name = "提高1 不提高0")
    private String enhanceFlag;
    //综合能力
    @Excel(name = "优3 良2 及格1 不及格0")
    private String capacityLevel;

    /** 头像 */
//    @Excel(name = "头像")
    private String avatar;

    /** 班级 */
    //前端传入
//    @Excel(name = "班级")
    private Long classId;

    /** 班级名称 */
    private String className;

    private Long userId;

    /** 计算机能力 */
    @Excel(name = "计算机能力")
    private String capacityComputer;

    /** 语言能力 */
    @Excel(name = "语言能力")
    private String capacityLanguage;

    /** 专业能力 */
    @Excel(name = "专业能力")
    private String capacityMajor;

    /** 个人评价 */
    @Excel(name = "个人评价")
    private String evaluatePerson;

    /** 工作态度 */
    @Excel(name = "工作态度")
    private String evaluateWork;

    /** 教育经历信息信息 */
    private List<SysEducation> sysEducationList;

    //用来取数据时候进行导入
    @Excel(name = "教育经历")
    private Long eduCount;

    //=========教育经历===================
    /** 上学时间 */
    @Excel(name = "上学时间")
    private String eduPeriod;

    /** 学校（院） */
    @Excel(name = "学校")
    private String college;

    /** 专业 */
    @Excel(name = "当时的专业")
    private String majorLast;

    /** 学历 */
    @Excel(name = "当时的学历")
    private String eduLast;

    /** 主修课程 */
    @Excel(name = "主修课程")
    private String course;
    //=========教育经历===================

    //========成绩统计===================
    //优的数量
    private Integer level3;
    //良的数量
    private Integer level2;
    //及格的数量
    private Integer level1;
    //不及格的数量
    private Integer level0;
    //某个classId的学生总数
    private Integer classStuCount;
    //提升的数量
    private Integer enhanceCount;
    //========成绩统计==================


    public Long getEduCount() {
        return eduCount;
    }

    public void setEduCount(Long eduCount) {
        this.eduCount = eduCount;
    }

    public String getEduPeriod() {
        return eduPeriod;
    }

    public void setEduPeriod(String eduPeriod) {
        this.eduPeriod = eduPeriod;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajorLast() {
        return majorLast;
    }

    public void setMajorLast(String majorLast) {
        this.majorLast = majorLast;
    }

    public String getEduLast() {
        return eduLast;
    }

    public void setEduLast(String eduLast) {
        this.eduLast = eduLast;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCapacityLevel() {
        return capacityLevel;
    }

    public void setCapacityLevel(String capacityLevel) {
        this.capacityLevel = capacityLevel;
    }

    public String getEnhanceFlag() {
        return enhanceFlag;
    }

    public void setEnhanceFlag(String enhanceFlag) {
        this.enhanceFlag = enhanceFlag;
    }

    public Integer getEnhanceCount() {
        return enhanceCount;
    }

    public void setEnhanceCount(Integer enhanceCount) {
        this.enhanceCount = enhanceCount;
    }

    public Integer getClassStuCount() {
        return classStuCount;
    }

    public void setClassStuCount(Integer classStuCount) {
        this.classStuCount = classStuCount;
    }

    public Integer getLevel3() {
        return level3;
    }

    public void setLevel3(Integer level3) {
        this.level3 = level3;
    }

    public Integer getLevel2() {
        return level2;
    }

    public void setLevel2(Integer level2) {
        this.level2 = level2;
    }

    public Integer getLevel1() {
        return level1;
    }

    public void setLevel1(Integer level1) {
        this.level1 = level1;
    }

    public Integer getLevel0() {
        return level0;
    }

    public void setLevel0(Integer level0) {
        this.level0 = level0;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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


    public boolean isEmpty(){
        return
                level3 == null &&
                level2 == null &&
                level1 == null &&
                level0 == null &&
                enhanceCount == null;
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
