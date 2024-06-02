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
import com.ruoyi.teach.domain.SysEvaluate;
import com.ruoyi.teach.service.ISysEvaluateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评教信息Controller
 *
 * @author sqc
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/teach/evaluate")
public class SysEvaluateController extends BaseController
{
    @Autowired
    private ISysEvaluateService sysEvaluateService;

    /**
     * 查询评教信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysEvaluate sysEvaluate)
    {
        startPage();
        List<SysEvaluate> list = sysEvaluateService.selectSysEvaluateList(sysEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出评教信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:export')")
    @Log(title = "评教信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEvaluate sysEvaluate)
    {
        List<SysEvaluate> list = sysEvaluateService.selectSysEvaluateList(sysEvaluate);
        ExcelUtil<SysEvaluate> util = new ExcelUtil<SysEvaluate>(SysEvaluate.class);
        util.exportExcel(response, list, "评教信息数据");
    }

    /**
     * 获取评教信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysEvaluateService.selectSysEvaluateById(id));
    }

    /**
     * 新增评教信息
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:add')")
    @Log(title = "评教信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEvaluate sysEvaluate)
    {
        return toAjax(sysEvaluateService.insertSysEvaluate(sysEvaluate));
    }

    /**
     * 修改评教信息
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:edit')")
    @Log(title = "评教信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEvaluate sysEvaluate)
    {
        return toAjax(sysEvaluateService.updateSysEvaluate(sysEvaluate));
    }

    /**
     * 删除评教信息
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:remove')")
    @Log(title = "评教信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysEvaluateService.deleteSysEvaluateByIds(ids));
    }

    /**
     * 查看学员是否提交评教
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:query')")
    @GetMapping(value = "/queryEvaluate")
    public AjaxResult queryEvaluateByUserId(){
        return success(sysEvaluateService.queryEvaluateByUserId(getUserId()));
    }

    /**
     * 查看分期评教分析结果
     */
    @PreAuthorize("@ss.hasPermi('teach:evaluate:query')")
    @GetMapping(value = "/getEvaluateResult/{classId}")
    public AjaxResult getEvaluateResult(@PathVariable Long classId){
        return success(sysEvaluateService.getEvaluateResult(classId));
    }
}
