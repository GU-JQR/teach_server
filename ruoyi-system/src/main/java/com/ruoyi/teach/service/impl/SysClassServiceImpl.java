package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysClassMapper;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.service.ISysClassService;

/**
 * 学员分期Service业务层处理
 * 
 * @author sqc
 * @date 2024-03-05
 */
@Service
public class SysClassServiceImpl implements ISysClassService 
{
    @Autowired
    private SysClassMapper sysClassMapper;

    /**
     * 查询学员分期
     * 
     * @param id 学员分期主键
     * @return 学员分期
     */
    @Override
    public SysClass selectSysClassById(Long id)
    {
        return sysClassMapper.selectSysClassById(id);
    }

    /**
     * 查询学员分期列表
     * 
     * @param sysClass 学员分期
     * @return 学员分期
     */
    @Override
    public List<SysClass> selectSysClassList(SysClass sysClass)
    {
        return sysClassMapper.selectSysClassList(sysClass);
    }

    /**
     * 新增学员分期
     * 
     * @param sysClass 学员分期
     * @return 结果
     */
    @Override
    public int insertSysClass(SysClass sysClass)
    {
        sysClass.setCreateTime(DateUtils.getNowDate());
        return sysClassMapper.insertSysClass(sysClass);
    }

    /**
     * 修改学员分期
     * 
     * @param sysClass 学员分期
     * @return 结果
     */
    @Override
    public int updateSysClass(SysClass sysClass)
    {
        sysClass.setUpdateTime(DateUtils.getNowDate());
        return sysClassMapper.updateSysClass(sysClass);
    }

    /**
     * 批量删除学员分期
     * 
     * @param ids 需要删除的学员分期主键
     * @return 结果
     */
    @Override
    public int deleteSysClassByIds(Long[] ids)
    {
        return sysClassMapper.deleteSysClassByIds(ids);
    }

    /**
     * 删除学员分期信息
     * 
     * @param id 学员分期主键
     * @return 结果
     */
    @Override
    public int deleteSysClassById(Long id)
    {
        return sysClassMapper.deleteSysClassById(id);
    }
}
