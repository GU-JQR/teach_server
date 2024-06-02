package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysTechEvaluate;

/**
 * 评学信息Service接口
 * 
 * @author sqc
 * @date 2024-04-05
 */
public interface ISysTechEvaluateService 
{
    /**
     * 查询评学信息
     * 
     * @param id 评学信息主键
     * @return 评学信息
     */
    public SysTechEvaluate selectSysTechEvaluateById(Long id);

    /**
     * 查询评学信息列表
     * 
     * @param sysTechEvaluate 评学信息
     * @return 评学信息集合
     */
    public List<SysTechEvaluate> selectSysTechEvaluateList(SysTechEvaluate sysTechEvaluate);

    /**
     * 新增评学信息
     * 
     * @param sysTechEvaluate 评学信息
     * @return 结果
     */
    public int insertSysTechEvaluate(SysTechEvaluate sysTechEvaluate);

    /**
     * 修改评学信息
     * 
     * @param sysTechEvaluate 评学信息
     * @return 结果
     */
    public int updateSysTechEvaluate(SysTechEvaluate sysTechEvaluate);

    /**
     * 批量删除评学信息
     * 
     * @param ids 需要删除的评学信息主键集合
     * @return 结果
     */
    public int deleteSysTechEvaluateByIds(Long[] ids);

    /**
     * 删除评学信息信息
     * 
     * @param id 评学信息主键
     * @return 结果
     */
    public int deleteSysTechEvaluateById(Long id);

    /**
     * 查看教员是否提交评学
     * @param userId
     * @return
     */
    public boolean queryTechEvaluateByUserId(Long userId,Long classId);

    /**
     * 查看分期评学分析结果
     * @param classId
     * @return
     */
    public Map<String,Object> getTechEvaluateResult(Long classId);
}
