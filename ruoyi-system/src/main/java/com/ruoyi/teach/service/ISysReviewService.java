package com.ruoyi.teach.service;

import java.util.List;
import com.ruoyi.teach.domain.SysReview;

/**
 * 审课记录Service接口
 * 
 * @author ruoyi
 * @date 2024-03-09
 */
public interface ISysReviewService 
{
    /**
     * 查询审课记录
     * 
     * @param id 审课记录主键
     * @return 审课记录
     */
    public SysReview selectSysReviewById(Long id);

    /**
     * 查询审课记录列表
     * 
     * @param sysReview 审课记录
     * @return 审课记录集合
     */
    public List<SysReview> selectSysReviewList(SysReview sysReview);

    /**
     * 新增审课记录
     * 
     * @param sysReview 审课记录
     * @return 结果
     */
    public int insertSysReview(SysReview sysReview);

    /**
     * 修改审课记录
     * 
     * @param sysReview 审课记录
     * @return 结果
     */
    public int updateSysReview(SysReview sysReview);

    /**
     * 批量删除审课记录
     * 
     * @param ids 需要删除的审课记录主键集合
     * @return 结果
     */
    public int deleteSysReviewByIds(Long[] ids);

    /**
     * 删除审课记录信息
     * 
     * @param id 审课记录主键
     * @return 结果
     */
    public int deleteSysReviewById(Long id);
}
