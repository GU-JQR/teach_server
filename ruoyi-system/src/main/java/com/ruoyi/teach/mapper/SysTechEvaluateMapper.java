package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTechEvaluate;
import org.apache.ibatis.annotations.Param;

/**
 * 评学信息Mapper接口
 * 
 * @author sqc
 * @date 2024-04-05
 */
public interface SysTechEvaluateMapper 
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
     * 根据期数查询评学信息列表
     *
     * @param classId 期数
     * @return 评学信息集合
     */
    public List<SysTechEvaluate> selectSysTechEvaluateListByClassId(Long classId);

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
     * 删除评学信息
     * 
     * @param id 评学信息主键
     * @return 结果
     */
    public int deleteSysTechEvaluateById(Long id);

    /**
     * 批量删除评学信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTechEvaluateByIds(Long[] ids);

    /**
     * 查看教员是否提交评学
     * @param userId
     * @return
     */
    public int queryTechEvaluateByUserId(@Param("userId") Long userId, @Param("classId") Long classId);
}
