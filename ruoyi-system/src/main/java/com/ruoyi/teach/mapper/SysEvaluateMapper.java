package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.teach.domain.SysEvaluate;

/**
 * 评教信息Mapper接口
 * 
 * @author sqc
 * @date 2024-03-10
 */
public interface SysEvaluateMapper 
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
     * 根据期数查询评教信息列表
     *
     * @param classId 期数
     * @return 评教信息集合
     */
    public List<SysEvaluate> selectSysEvaluateListByClassId(Long classId);

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
     * 删除评教信息
     * 
     * @param id 评教信息主键
     * @return 结果
     */
    public int deleteSysEvaluateById(Long id);

    /**
     * 批量删除评教信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysEvaluateByIds(Long[] ids);

    /**
     * 查看学员是否提交评教
     * @param userId
     * @return
     */
    public int queryEvaluateByUserId(Long userId);
}
