package com.ruoyi.teach.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 书籍信息对象 sys_book
 * 
 * @author sqc
 * @date 2024-03-04
 */
public class SysBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标签ID */
    @Excel(name = "标签ID")
    private Long labelId;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 封面 */
    private String cover;

    /** 地址 */
    private String url;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLabelId(Long labelId) 
    {
        this.labelId = labelId;
    }

    public Long getLabelId() 
    {
        return labelId;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("labelId", getLabelId())
            .append("bookName", getBookName())
            .append("author", getAuthor())
            .append("country", getCountry())
            .append("cover", getCover())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
