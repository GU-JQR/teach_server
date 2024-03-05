package com.ruoyi.teach.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysBookMapper;
import com.ruoyi.teach.domain.SysBook;
import com.ruoyi.teach.service.ISysBookService;

/**
 * 书籍信息Service业务层处理
 * 
 * @author sqc
 * @date 2024-03-04
 */
@Service
public class SysBookServiceImpl implements ISysBookService 
{
    @Autowired
    private SysBookMapper sysBookMapper;

    /**
     * 查询书籍信息
     * 
     * @param id 书籍信息主键
     * @return 书籍信息
     */
    @Override
    public SysBook selectSysBookById(Long id)
    {
        return sysBookMapper.selectSysBookById(id);
    }

    /**
     * 查询书籍信息列表
     * 
     * @param sysBook 书籍信息
     * @return 书籍信息
     */
    @Override
    public List<SysBook> selectSysBookList(SysBook sysBook)
    {
        return sysBookMapper.selectSysBookList(sysBook);
    }

    /**
     * 新增书籍信息
     * 
     * @param sysBook 书籍信息
     * @return 结果
     */
    @Override
    public int insertSysBook(SysBook sysBook)
    {
        sysBook.setCreateTime(DateUtils.getNowDate());
        return sysBookMapper.insertSysBook(sysBook);
    }

    /**
     * 修改书籍信息
     * 
     * @param sysBook 书籍信息
     * @return 结果
     */
    @Override
    public int updateSysBook(SysBook sysBook)
    {
        sysBook.setUpdateTime(DateUtils.getNowDate());
        return sysBookMapper.updateSysBook(sysBook);
    }

    /**
     * 批量删除书籍信息
     * 
     * @param ids 需要删除的书籍信息主键
     * @return 结果
     */
    @Override
    public int deleteSysBookByIds(Long[] ids)
    {
        return sysBookMapper.deleteSysBookByIds(ids);
    }

    /**
     * 删除书籍信息信息
     * 
     * @param id 书籍信息主键
     * @return 结果
     */
    @Override
    public int deleteSysBookById(Long id)
    {
        return sysBookMapper.deleteSysBookById(id);
    }
}
