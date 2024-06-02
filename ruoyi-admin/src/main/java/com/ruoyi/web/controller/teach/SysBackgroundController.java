package com.ruoyi.web.controller.teach;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.teach.domain.SysBackground;
import com.ruoyi.teach.service.ISysBackgroundService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 背景图信息Controller
 *
 * @author sqc
 * @date 2024-03-15
 */
@RestController
@RequestMapping("/teach/background")
public class SysBackgroundController extends BaseController
{
    @Autowired
    private ISysBackgroundService sysBackgroundService;

    /**
     * 查询背景图信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:background:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBackground sysBackground)
    {
        startPage();
        List<SysBackground> list = sysBackgroundService.selectSysBackgroundList(sysBackground);
        return getDataTable(list);
    }

    /**
     * 查询全部背景图信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:background:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(SysBackground sysBackground)
    {
        return success(sysBackgroundService.selectSysBackgroundList(sysBackground));
    }

    /**
     * 导出背景图信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:background:export')")
    @Log(title = "背景图信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBackground sysBackground)
    {
        List<SysBackground> list = sysBackgroundService.selectSysBackgroundList(sysBackground);
        ExcelUtil<SysBackground> util = new ExcelUtil<SysBackground>(SysBackground.class);
        util.exportExcel(response, list, "背景图信息数据");
    }

    /**
     * 获取背景图信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:background:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysBackgroundService.selectSysBackgroundById(id));
    }

    /**
     * 新增背景图信息
     */
    @PreAuthorize("@ss.hasPermi('teach:background:add')")
    @Log(title = "背景图信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBackground sysBackground)
    {
        return toAjax(sysBackgroundService.insertSysBackground(sysBackground));
    }

    /**
     * 修改背景图信息
     */
    @PreAuthorize("@ss.hasPermi('teach:background:edit')")
    @Log(title = "背景图信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBackground sysBackground)
    {
        return toAjax(sysBackgroundService.updateSysBackground(sysBackground));
    }

    /**
     * 删除背景图信息
     */
    @PreAuthorize("@ss.hasPermi('teach:background:remove')")
    @Log(title = "背景图信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBackgroundService.deleteSysBackgroundByIds(ids));
    }
}
