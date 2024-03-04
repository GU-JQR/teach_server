package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysLabel;
import org.apache.ibatis.annotations.Param;

/**
 * 标签信息Mapper接口
 * 
 * @author sqc
 * @date 2024-02-28
 */
public interface SysLabelMapper 
{
    /**
     * 查询标签信息
     * 
     * @param labelId 标签信息主键
     * @return 标签信息
     */
    public SysLabel selectSysLabelByLabelId(Long labelId);

    /**
     * 根据ID查询所有子标签
     *
     * @param labelId 标签ID
     * @return 标签列表
     */
    public List<SysLabel> selectChildrenLabelById(Long labelId);

    /**
     * 查询标签信息列表
     * 
     * @param sysLabel 标签信息
     * @return 标签信息集合
     */
    public List<SysLabel> selectSysLabelList(SysLabel sysLabel);

    /**
     * 新增标签信息
     * 
     * @param sysLabel 标签信息
     * @return 结果
     */
    public int insertSysLabel(SysLabel sysLabel);

    /**
     * 修改标签信息
     * 
     * @param sysLabel 标签信息
     * @return 结果
     */
    public int updateSysLabel(SysLabel sysLabel);

    /**
     * 删除标签信息
     * 
     * @param labelId 标签信息主键
     * @return 结果
     */
    public int deleteSysLabelByLabelId(Long labelId);

    /**
     * 修改子元素关系
     *
     * @param labels 子元素
     * @return 结果
     */
    public int updateLabelChildren(@Param("labels") List<SysLabel> labels);

    /**
     * 修改所在标签正常状态
     *
     * @param labelIds 标签ID组
     */
    public void updateLabelStatusNormal(Long[] labelIds);

    /**
     * 根据parentId更新标签中父级标签名称
     * @param sysLabel
     */
    public void updateParentNameByParentId(SysLabel sysLabel);

    /**
     * 批量删除标签信息
     * 
     * @param labelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysLabelByLabelIds(Long[] labelIds);

}
