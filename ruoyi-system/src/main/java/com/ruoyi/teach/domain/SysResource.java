package com.ruoyi.teach.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源库对象 sys_resource
 *
 * @author ruoyi
 * @date 2024-02-28
 */
public class SysResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;


    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 资源日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "资源日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 简介 */
    @Excel(name = "简介")
    private String note;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private Integer typeId;

    /** 资源类型名称 */
    @Excel(name = "资源类型名称")
    private String typeName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String addressFile;

    // labelId 查询typeID
    private Long labelId;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }
    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }
    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }

    public Integer getTypeId()
    {
        return typeId;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setAddressFile(String addressFile)
    {
        this.addressFile = addressFile;
    }

    public String getAddressFile()
    {
        return addressFile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("date", getDate())
            .append("note", getNote())
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("addressFile", getAddressFile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
