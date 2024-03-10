package com.ruoyi.teach.service.impl;

import java.time.LocalDateTime;
import java.util.*;

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
    /**
     * @description:根据类型统计个数
     * @return: java.util.List<java.util.Map < java.lang.Integer, java.lang.Long>>
     **/

    @Override
    public long[] countByCategoryType() {
        List<Map<String, Long>> list = sysResourceMapper.countByCategoryType();
        long[] doubles = new long[8];
        for (Map<String, Long> resourceMap : list) {
            Long typeId = Long.parseLong(String.valueOf(resourceMap.get("type_id"))); // 使用getOrDefault避免NullPointerException
            Long allQuantity = resourceMap.getOrDefault("a", 0L); // 避免除以0的情况，默认至少有一个资源}
            doubles[typeId.intValue()]=allQuantity;
        }
        return doubles;

    }

    @Override
    public double[] getUpdateDate() {
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMinusOneYear = now.minusYears(1);
        List<Map<String, Long>> list = sysResourceMapper.getUpdateDate(nowMinusOneYear);
        double[] doubles = new double[8];
        for (Map<String, Long> resourceMap : list) {
            Long typeId = Long.parseLong(String.valueOf(resourceMap.get("type_id"))); // 使用getOrDefault避免NullPointerException
            Long thisYearUpdateQuantity = resourceMap.getOrDefault("thisYearUpdateQuantity", 0L);
            Long allQuantity = resourceMap.getOrDefault("allQuantity", 0L); // 避免除以0的情况，默认至少有一个资源
            // 检查是否有今年更新的数量，避免除以0
            if (allQuantity == 0) {
                doubles[typeId.intValue()]=0.0;
                continue;
            }
            // 计算更新率，确保至少有一个资源以避免除以0
            double updateRate = (double) thisYearUpdateQuantity / allQuantity;
            doubles[typeId.intValue()]=updateRate;
        }
        return doubles;
    }
}
