package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.SysResource;

/**
 * 资源库Service接口
 * 
 * @author ruoyi
 * @date 2024-02-28
 */
public interface ISysResourceService 
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
     * 批量删除资源库
     * 
     * @param ids 需要删除的资源库主键集合
     * @return 结果
     */
    public int deleteSysResourceByIds(Long[] ids);

    /**
     * 删除资源库信息
     * 
     * @param id 资源库主键
     * @return 结果
     */
    public int deleteSysResourceById(Long id);
}
