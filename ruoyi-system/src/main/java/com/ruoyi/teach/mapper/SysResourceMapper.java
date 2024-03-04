package com.ruoyi.teach.mapper;


import com.ruoyi.teach.domain.SysResource;

import java.util.List;

/**
 * 资源库Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-03
 */
public interface SysResourceMapper
{
    /**
     * 查询资源库
     *
     * @param id 资源库主键
     * @return 资源库
     */
    public SysResource selectSysResourceById(Long id);

    /**
     * 查询资源库列表
     *
     * @param sysResource 资源库
     * @return 资源库集合
     */
    public List<SysResource> selectSysResourceList(SysResource sysResource);

    /**
     * 新增资源库
     *
     * @param sysResource 资源库
     * @return 结果
     */
    public int insertSysResource(SysResource sysResource);

    /**
     * 修改资源库
     *
     * @param sysResource 资源库
     * @return 结果
     */
    public int updateSysResource(SysResource sysResource);

    /**
     * 删除资源库
     *
     * @param id 资源库主键
     * @return 结果
     */
    public int deleteSysResourceById(Long id);

    /**
     * 批量删除资源库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysResourceByIds(Long[] ids);
}
