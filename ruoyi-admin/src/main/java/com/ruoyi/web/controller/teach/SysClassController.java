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
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.service.ISysClassService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学员分期Controller
 * 
 * @author sqc
 * @date 2024-03-05
 */
@RestController
@RequestMapping("/teach/class")
public class SysClassController extends BaseController
{
    @Autowired
    private ISysClassService sysClassService;

    /**
     * 查询学员分期列表
     */
    @PreAuthorize("@ss.hasPermi('teach:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysClass sysClass)
    {
        startPage();
        List<SysClass> list = sysClassService.selectSysClassList(sysClass);
        return getDataTable(list);
    }

    /**
     * 查询全部学员分期列表
     */
    @PreAuthorize("@ss.hasPermi('teach:class:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(SysClass sysClass)
    {
        return AjaxResult.success(sysClassService.selectSysClassList(sysClass));
    }

    /**
     * 导出学员分期列表
     */
    @PreAuthorize("@ss.hasPermi('teach:class:export')")
    @Log(title = "学员分期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysClass sysClass)
    {
        List<SysClass> list = sysClassService.selectSysClassList(sysClass);
        ExcelUtil<SysClass> util = new ExcelUtil<SysClass>(SysClass.class);
        util.exportExcel(response, list, "学员分期数据");
    }

    /**
     * 获取学员分期详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:class:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysClassService.selectSysClassById(id));
    }

    /**
     * 新增学员分期
     */
    @PreAuthorize("@ss.hasPermi('teach:class:add')")
    @Log(title = "学员分期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysClass sysClass)
    {
        return toAjax(sysClassService.insertSysClass(sysClass));
    }

    /**
     * 修改学员分期
     */
    @PreAuthorize("@ss.hasPermi('teach:class:edit')")
    @Log(title = "学员分期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysClass sysClass)
    {
        return toAjax(sysClassService.updateSysClass(sysClass));
    }

    /**
     * 删除学员分期
     */
    @PreAuthorize("@ss.hasPermi('teach:class:remove')")
    @Log(title = "学员分期", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysClassService.deleteSysClassByIds(ids));
    }
}
