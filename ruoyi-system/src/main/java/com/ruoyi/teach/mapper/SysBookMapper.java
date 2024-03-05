package com.ruoyi.teach.mapper;

import java.util.List;
import com.ruoyi.teach.domain.SysBook;

/**
 * 书籍信息Mapper接口
 * 
 * @author sqc
 * @date 2024-03-04
 */
public interface SysBookMapper 
{
    /**
     * 查询书籍信息
     * 
     * @param id 书籍信息主键
     * @return 书籍信息
     */
    public SysBook selectSysBookById(Long id);

    /**
     * 查询书籍信息列表
     * 
     * @param sysBook 书籍信息
     * @return 书籍信息集合
     */
    public List<SysBook> selectSysBookList(SysBook sysBook);

    /**
     * 新增书籍信息
     * 
     * @param sysBook 书籍信息
     * @return 结果
     */
    public int insertSysBook(SysBook sysBook);

    /**
     * 修改书籍信息
     * 
     * @param sysBook 书籍信息
     * @return 结果
     */
    public int updateSysBook(SysBook sysBook);

    /**
     * 删除书籍信息
     * 
     * @param id 书籍信息主键
     * @return 结果
     */
    public int deleteSysBookById(Long id);

    /**
     * 批量删除书籍信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBookByIds(Long[] ids);
}
