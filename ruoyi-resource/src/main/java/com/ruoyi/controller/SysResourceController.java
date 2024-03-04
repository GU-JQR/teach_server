package com.ruoyi.controller;

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
import com.ruoyi.domain.SysResource;
import com.ruoyi.service.ISysResourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资源库Controller
 * 
 * @author ruoyi
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/resource/resource")
public class SysResourceController extends BaseController
{
    @Autowired
    private ISysResourceService sysResourceService;

    /**
     * 查询资源库列表
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysResource sysResource)
    {
        startPage();
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        return getDataTable(list);
    }

    /**
     * 导出资源库列表
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:export')")
    @Log(title = "资源库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysResource sysResource)
    {
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        ExcelUtil<SysResource> util = new ExcelUtil<SysResource>(SysResource.class);
        util.exportExcel(response, list, "资源库数据");
    }

    /**
     * 获取资源库详细信息
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysResourceService.selectSysResourceById(id));
    }

    /**
     * 新增资源库
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:add')")
    @Log(title = "资源库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysResource sysResource)
    {
        return toAjax(sysResourceService.insertSysResource(sysResource));
    }

    /**
     * 修改资源库
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:edit')")
    @Log(title = "资源库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysResource sysResource)
    {
        return toAjax(sysResourceService.updateSysResource(sysResource));
    }

    /**
     * 删除资源库
     */
    @PreAuthorize("@ss.hasPermi('resource:resource:remove')")
    @Log(title = "资源库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysResourceService.deleteSysResourceByIds(ids));
    }
}
