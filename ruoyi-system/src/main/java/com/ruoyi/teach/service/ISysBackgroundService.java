package com.ruoyi.teach.service;

import java.util.List;
import com.ruoyi.teach.domain.SysBackground;

/**
 * 背景图信息Service接口
 * 
 * @author sqc
 * @date 2024-03-15
 */
public interface ISysBackgroundService 
{
    /**
     * 查询背景图信息
     * 
     * @param id 背景图信息主键
     * @return 背景图信息
     */
    public SysBackground selectSysBackgroundById(Long id);

    /**
     * 查询背景图信息列表
     * 
     * @param sysBackground 背景图信息
     * @return 背景图信息集合
     */
    public List<SysBackground> selectSysBackgroundList(SysBackground sysBackground);

    /**
     * 新增背景图信息
     * 
     * @param sysBackground 背景图信息
     * @return 结果
     */
    public int insertSysBackground(SysBackground sysBackground);

    /**
     * 修改背景图信息
     * 
     * @param sysBackground 背景图信息
     * @return 结果
     */
    public int updateSysBackground(SysBackground sysBackground);

    /**
     * 批量删除背景图信息
     * 
     * @param ids 需要删除的背景图信息主键集合
     * @return 结果
     */
    public int deleteSysBackgroundByIds(Long[] ids);

    /**
     * 删除背景图信息信息
     * 
     * @param id 背景图信息主键
     * @return 结果
     */
    public int deleteSysBackgroundById(Long id);
}
