package com.ruoyi.teach.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.teach.domain.SysEvaluate;

/**
 * 评教信息Service接口
 * 
 * @author sqc
 * @date 2024-03-10
 */
public interface ISysEvaluateService 
{
    /**
     * 查询评教信息
     * 
     * @param id 评教信息主键
     * @return 评教信息
     */
    public SysEvaluate selectSysEvaluateById(Long id);

    /**
     * 查询评教信息列表
     * 
     * @param sysEvaluate 评教信息
     * @return 评教信息集合
     */
    public List<SysEvaluate> selectSysEvaluateList(SysEvaluate sysEvaluate);

    /**
     * 新增评教信息
     * 
     * @param sysEvaluate 评教信息
     * @return 结果
     */
    public int insertSysEvaluate(SysEvaluate sysEvaluate);

    /**
     * 修改评教信息
     * 
     * @param sysEvaluate 评教信息
     * @return 结果
     */
    public int updateSysEvaluate(SysEvaluate sysEvaluate);

    /**
     * 批量删除评教信息
     * 
     * @param ids 需要删除的评教信息主键集合
     * @return 结果
     */
    public int deleteSysEvaluateByIds(Long[] ids);

    /**
     * 删除评教信息信息
     * 
     * @param id 评教信息主键
     * @return 结果
     */
    public int deleteSysEvaluateById(Long id);

    /**
     * 查看学员是否提交评教
     * @param userId
     * @return
     */
    public boolean queryEvaluateByUserId(Long userId);

    /**
     * 查看分期评教分析结果
     * @param classId
     * @return
     */
    public Map<String,Object> getEvaluateResult(Long classId);
}
