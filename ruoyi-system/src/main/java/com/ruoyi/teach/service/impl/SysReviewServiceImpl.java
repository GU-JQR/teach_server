package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysReviewMapper;
import com.ruoyi.teach.domain.SysReview;
import com.ruoyi.teach.service.ISysReviewService;

/**
 * 审课记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-09
 */
@Service
public class SysReviewServiceImpl implements ISysReviewService 
{
    @Autowired
    private SysReviewMapper sysReviewMapper;

    /**
     * 查询审课记录
     * 
     * @param id 审课记录主键
     * @return 审课记录
     */
    @Override
    public SysReview selectSysReviewById(Long id)
    {
        return sysReviewMapper.selectSysReviewById(id);
    }

    /**
     * 查询审课记录列表
     * 
     * @param sysReview 审课记录
     * @return 审课记录
     */
    @Override
    public List<SysReview> selectSysReviewList(SysReview sysReview)
    {
        return sysReviewMapper.selectSysReviewList(sysReview);
    }

    /**
     * 新增审课记录
     * 
     * @param sysReview 审课记录
     * @return 结果
     */
    @Override
    public int insertSysReview(SysReview sysReview)
    {
        sysReview.setCreateTime(DateUtils.getNowDate());
        return sysReviewMapper.insertSysReview(sysReview);
    }

    /**
     * 修改审课记录
     * 
     * @param sysReview 审课记录
     * @return 结果
     */
    @Override
    public int updateSysReview(SysReview sysReview)
    {
        sysReview.setUpdateTime(DateUtils.getNowDate());
        return sysReviewMapper.updateSysReview(sysReview);
    }

    /**
     * 批量删除审课记录
     * 
     * @param ids 需要删除的审课记录主键
     * @return 结果
     */
    @Override
    public int deleteSysReviewByIds(Long[] ids)
    {
        return sysReviewMapper.deleteSysReviewByIds(ids);
    }

    /**
     * 删除审课记录信息
     * 
     * @param id 审课记录主键
     * @return 结果
     */
    @Override
    public int deleteSysReviewById(Long id)
    {
        return sysReviewMapper.deleteSysReviewById(id);
    }
}
