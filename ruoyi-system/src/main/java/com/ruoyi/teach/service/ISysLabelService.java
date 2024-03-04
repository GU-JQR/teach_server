package com.ruoyi.teach.service;

import java.util.List;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysLabel;

/**
 * 标签信息Service接口
 * 
 * @author sqc
 * @date 2024-02-28
 */
public interface ISysLabelService 
{
    /**
     * 查询标签信息
     * 
     * @param labelId 标签信息主键
     * @return 标签信息
     */
    public SysLabel selectSysLabelByLabelId(Long labelId);

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
     * 批量删除标签信息
     * 
     * @param labelIds 需要删除的标签信息主键集合
     * @return 结果
     */
    public int deleteSysLabelByLabelIds(Long[] labelIds);

    /**
     * 删除标签信息信息
     * 
     * @param labelId 标签信息主键
     * @return 结果
     */
    public int deleteSysLabelByLabelId(Long labelId);



    /**
     * 查询全部标签树结构信息
     *
     * @param label 部门信息
     * @return 标签树信息集合
     */
    public List<TreeSelect> selectLabelTreeList(SysLabel label);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param labels 标签列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildLabelTreeSelect(List<SysLabel> labels);

    /**
     * 构建前端所需要树结构
     *
     * @param labels 标签列表
     * @return 树结构列表
     */
    public List<SysLabel> buildLabelTree(List<SysLabel> labels);
}
