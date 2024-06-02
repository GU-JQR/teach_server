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
import com.ruoyi.teach.domain.SysReview;
import com.ruoyi.teach.service.ISysReviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审课记录Controller
 * 
 * @author ruoyi
 * @date 2024-03-09
 */
@RestController
@RequestMapping("/teach/review")
public class SysReviewController extends BaseController
{
    @Autowired
    private ISysReviewService sysReviewService;

    /**
     * 查询审课记录列表
     */
    @PreAuthorize("@ss.hasPermi('teach:review:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysReview sysReview)
    {
        startPage();
        List<SysReview> list = sysReviewService.selectSysReviewList(sysReview);
        return getDataTable(list);
    }

    /**
     * 导出审课记录列表
     */
    @PreAuthorize("@ss.hasPermi('teach:review:export')")
    @Log(title = "审课记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysReview sysReview)
    {
        List<SysReview> list = sysReviewService.selectSysReviewList(sysReview);
        ExcelUtil<SysReview> util = new ExcelUtil<SysReview>(SysReview.class);
        util.exportExcel(response, list, "审课记录数据");
    }

    /**
     * 获取审课记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:review:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysReviewService.selectSysReviewById(id));
    }

    /**
     * 新增审课记录
     */
    @PreAuthorize("@ss.hasPermi('teach:review:add')")
    @Log(title = "审课记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysReview sysReview)
    {
        return toAjax(sysReviewService.insertSysReview(sysReview));
    }

    /**
     * 修改审课记录
     */
    @PreAuthorize("@ss.hasPermi('teach:review:edit')")
    @Log(title = "审课记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysReview sysReview)
    {
        return toAjax(sysReviewService.updateSysReview(sysReview));
    }

    /**
     * 删除审课记录
     */
    @PreAuthorize("@ss.hasPermi('teach:review:remove')")
    @Log(title = "审课记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysReviewService.deleteSysReviewByIds(ids));
    }
}
