package com.ruoyi.teach.service;


import com.ruoyi.teach.domain.SysTalent;

import java.util.List;

/**
 * 人才管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-02
 */
public interface ISysTalentService 
{
    /**
     * 查询人才管理
     * 
     * @param id 人才管理主键
     * @return 人才管理
     */
    public SysTalent selectSysTalentById(Long id);

    /**
     * 查询人才管理列表
     * 
     * @param sysTalent 人才管理
     * @return 人才管理集合
     */
    public List<SysTalent> selectSysTalentList(SysTalent sysTalent);

    /**
     * 新增人才管理
     * 
     * @param sysTalent 人才管理
     * @return 结果
     */
    public int insertSysTalent(SysTalent sysTalent);

    /**
     * 修改人才管理
     * 
     * @param sysTalent 人才管理
     * @return 结果
     */
    public int updateSysTalent(SysTalent sysTalent);

    /**
     * 批量删除人才管理
     * 
     * @param ids 需要删除的人才管理主键集合
     * @return 结果
     */
    public int deleteSysTalentByIds(Long[] ids);

    /**
     * 删除人才管理信息
     * 
     * @param id 人才管理主键
     * @return 结果
     */
    public int deleteSysTalentById(Long id);
}
