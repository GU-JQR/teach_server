package com.ruoyi.web.controller.teach;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.teach.domain.SysBook;
import com.ruoyi.teach.service.ISysBookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 书籍信息Controller
 *
 * @author sqc
 * @date 2024-03-04
 */
@RestController
@RequestMapping("/teach/book")
public class SysBookController extends BaseController
{
    @Autowired
    private ISysBookService sysBookService;

    /**
     * 查询书籍信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBook sysBook)
    {
        startPage();
        List<SysBook> list = sysBookService.selectSysBookList(sysBook);
        return getDataTable(list);
    }

    /**
     * 导出书籍信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:book:export')")
    @Log(title = "书籍信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBook sysBook)
    {
        List<SysBook> list = sysBookService.selectSysBookList(sysBook);
        ExcelUtil<SysBook> util = new ExcelUtil<SysBook>(SysBook.class);
        util.exportExcel(response, list, "书籍信息数据");
    }

    /**
     * 获取书籍信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysBookService.selectSysBookById(id));
    }

    /**
     * 新增书籍信息
     */
    @PreAuthorize("@ss.hasPermi('teach:book:add')")
    @Log(title = "书籍信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBook sysBook)
    {
        sysBook.setCreateBy(getUserId().toString());
        return toAjax(sysBookService.insertSysBook(sysBook));
    }

    /**
     * 修改书籍信息
     */
    @PreAuthorize("@ss.hasPermi('teach:book:edit')")
    @Log(title = "书籍信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBook sysBook)
    {
        return toAjax(sysBookService.updateSysBook(sysBook));
    }

    /**
     * 删除书籍信息
     */
    @PreAuthorize("@ss.hasPermi('teach:book:remove')")
    @Log(title = "书籍信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBookService.deleteSysBookByIds(ids));
    }
}
