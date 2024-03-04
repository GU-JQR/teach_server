package com.ruoyi.teach.service.impl;


import com.ruoyi.common.core.domain.entity.SysLabel;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.teach.domain.SysTalent;
import com.ruoyi.teach.mapper.SysTalentMapper;
import com.ruoyi.teach.service.ISysTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-02
 */
@Service
public class SysTalentServiceImpl implements ISysTalentService
{
    @Autowired
    private SysTalentMapper sysTalentMapper;

    /**
     * 查询人才管理
     * 
     * @param id 人才管理主键
     * @return 人才管理
     */
    @Override
    public SysTalent selectSysTalentById(Long id)
    {
        return sysTalentMapper.selectSysTalentById(id);
    }

    /**
     * 查询人才管理列表
     * 
     * @param sysTalent 人才管理
     * @return 人才管理
     */
    @Override
    public List<SysTalent> selectSysTalentList(SysTalent sysTalent)
    {

        return sysTalentMapper.selectSysTalentList(sysTalent);
    }

    /**
     * 新增人才管理
     * 
     * @param sysTalent 人才管理
     * @return 结果
     */
    @Override
    public int insertSysTalent(SysTalent sysTalent)
    {
        return sysTalentMapper.insertSysTalent(sysTalent);
    }

    /**
     * 修改人才管理
     * 
     * @param sysTalent 人才管理
     * @return 结果
     */
    @Override
    public int updateSysTalent(SysTalent sysTalent)
    {
        sysTalent.setUpdateTime(DateUtils.getNowDate());
        return sysTalentMapper.updateSysTalent(sysTalent);
    }

    /**
     * 批量删除人才管理
     * 
     * @param ids 需要删除的人才管理主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentByIds(Long[] ids)
    {
        return sysTalentMapper.deleteSysTalentByIds(ids);
    }

    /**
     * 删除人才管理信息
     * 
     * @param id 人才管理主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentById(Long id)
    {
        return sysTalentMapper.deleteSysTalentById(id);
    }
}
