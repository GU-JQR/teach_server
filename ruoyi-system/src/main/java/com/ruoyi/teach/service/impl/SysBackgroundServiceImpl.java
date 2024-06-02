package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysBackgroundMapper;
import com.ruoyi.teach.domain.SysBackground;
import com.ruoyi.teach.service.ISysBackgroundService;

/**
 * 背景图信息Service业务层处理
 * 
 * @author sqc
 * @date 2024-03-15
 */
@Service
public class SysBackgroundServiceImpl implements ISysBackgroundService 
{
    @Autowired
    private SysBackgroundMapper sysBackgroundMapper;

    /**
     * 查询背景图信息
     * 
     * @param id 背景图信息主键
     * @return 背景图信息
     */
    @Override
    public SysBackground selectSysBackgroundById(Long id)
    {
        return sysBackgroundMapper.selectSysBackgroundById(id);
    }

    /**
     * 查询背景图信息列表
     * 
     * @param sysBackground 背景图信息
     * @return 背景图信息
     */
    @Override
    public List<SysBackground> selectSysBackgroundList(SysBackground sysBackground)
    {
        return sysBackgroundMapper.selectSysBackgroundList(sysBackground);
    }

    /**
     * 新增背景图信息
     * 
     * @param sysBackground 背景图信息
     * @return 结果
     */
    @Override
    public int insertSysBackground(SysBackground sysBackground)
    {
        sysBackground.setCreateTime(DateUtils.getNowDate());
        return sysBackgroundMapper.insertSysBackground(sysBackground);
    }

    /**
     * 修改背景图信息
     * 
     * @param sysBackground 背景图信息
     * @return 结果
     */
    @Override
    public int updateSysBackground(SysBackground sysBackground)
    {
        sysBackground.setUpdateTime(DateUtils.getNowDate());
        return sysBackgroundMapper.updateSysBackground(sysBackground);
    }

    /**
     * 批量删除背景图信息
     * 
     * @param ids 需要删除的背景图信息主键
     * @return 结果
     */
    @Override
    public int deleteSysBackgroundByIds(Long[] ids)
    {
        return sysBackgroundMapper.deleteSysBackgroundByIds(ids);
    }

    /**
     * 删除背景图信息信息
     * 
     * @param id 背景图信息主键
     * @return 结果
     */
    @Override
    public int deleteSysBackgroundById(Long id)
    {
        return sysBackgroundMapper.deleteSysBackgroundById(id);
    }
}
