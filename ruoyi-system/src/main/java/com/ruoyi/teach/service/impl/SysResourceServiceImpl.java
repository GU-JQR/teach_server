package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.teach.domain.SysResource;
import com.ruoyi.teach.mapper.SysResourceMapper;
import com.ruoyi.teach.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 资源库Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-28
 */
@Service
public class SysResourceServiceImpl implements ISysResourceService
{
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 查询资源库
     *
     * @param id 资源库主键
     * @return 资源库
     */
    @Override
    public SysResource selectSysResourceById(Long id)
    {
        return sysResourceMapper.selectSysResourceById(id);
    }

    /**
     * 查询资源库列表
     *
     * @param sysResource 资源库
     * @return 资源库
     */
    @Override
    public List<SysResource> selectSysResourceList(SysResource sysResource)
    {
        return sysResourceMapper.selectSysResourceList(sysResource);
    }

    /**
     * 新增资源库
     *
     * @param sysResource 资源库
     * @return 结果
     */
    @Override
    public int insertSysResource(SysResource sysResource)
    {
        sysResource.setCreateTime(DateUtils.getNowDate());
        return sysResourceMapper.insertSysResource(sysResource);
    }

    /**
     * 修改资源库
     *
     * @param sysResource 资源库
     * @return 结果
     */
    @Override
    public int updateSysResource(SysResource sysResource)
    {
        sysResource.setUpdateTime(DateUtils.getNowDate());
        return sysResourceMapper.updateSysResource(sysResource);
    }

    /**
     * 批量删除资源库
     *
     * @param ids 需要删除的资源库主键
     * @return 结果
     */
    @Override
    public int deleteSysResourceByIds(Long[] ids)
    {
        return sysResourceMapper.deleteSysResourceByIds(ids);
    }

    /**
     * 删除资源库信息
     *
     * @param id 资源库主键
     * @return 结果
     */
    @Override
    public int deleteSysResourceById(Long id)
    {
        return sysResourceMapper.deleteSysResourceById(id);
    }
}
