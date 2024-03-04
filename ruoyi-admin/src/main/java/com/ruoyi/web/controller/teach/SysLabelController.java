package com.ruoyi.web.controller.teach;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysDept;
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
import com.ruoyi.common.core.domain.entity.SysLabel;
import com.ruoyi.teach.service.ISysLabelService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 标签信息Controller
 * 
 * @author sqc
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/teach/label")
public class SysLabelController extends BaseController
{
    @Autowired
    private ISysLabelService sysLabelService;

    /**
     * 查询标签信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:label:list')")
    @GetMapping("/list")
    public AjaxResult list(SysLabel sysLabel)
    {
        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
        return success(list);
    }

    /**
     * 导出标签信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:label:export')")
    @Log(title = "标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLabel sysLabel)
    {
        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
        ExcelUtil<SysLabel> util = new ExcelUtil<SysLabel>(SysLabel.class);
        util.exportExcel(response, list, "标签信息数据");
    }

    /**
     * 获取标签信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:label:query')")
    @GetMapping(value = "/{labelId}")
    public AjaxResult getInfo(@PathVariable("labelId") Long labelId)
    {
        return success(sysLabelService.selectSysLabelByLabelId(labelId));
    }

    /**
     * 新增标签信息
     */
    @PreAuthorize("@ss.hasPermi('teach:label:add')")
    @Log(title = "标签信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysLabel sysLabel)
    {
        return toAjax(sysLabelService.insertSysLabel(sysLabel));
    }

    /**
     * 修改标签信息
     */
    @PreAuthorize("@ss.hasPermi('teach:label:edit')")
    @Log(title = "标签信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysLabel sysLabel)
    {
        return toAjax(sysLabelService.updateSysLabel(sysLabel));
    }

    /**
     * 删除标签信息
     */
    @PreAuthorize("@ss.hasPermi('teach:label:remove')")
    @Log(title = "标签信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{labelIds}")
    public AjaxResult remove(@PathVariable Long[] labelIds)
    {
        return toAjax(sysLabelService.deleteSysLabelByLabelIds(labelIds));
    }

    /**
     * 获取标签树列表
     */
    @PreAuthorize("@ss.hasPermi('teach:label:list')")
    @GetMapping("/labelTree")
    public AjaxResult labelTree(SysLabel label)
    {
        return success(sysLabelService.selectLabelTreeList(label));
    }
}
