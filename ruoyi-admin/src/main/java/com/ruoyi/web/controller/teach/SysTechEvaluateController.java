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
import com.ruoyi.system.domain.SysTechEvaluate;
import com.ruoyi.system.service.ISysTechEvaluateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评学信息Controller
 * 
 * @author sqc
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/teach/techEvaluate")
public class SysTechEvaluateController extends BaseController
{
    @Autowired
    private ISysTechEvaluateService sysTechEvaluateService;

    /**
     * 查询评学信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTechEvaluate sysTechEvaluate)
    {
        startPage();
        List<SysTechEvaluate> list = sysTechEvaluateService.selectSysTechEvaluateList(sysTechEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出评学信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:export')")
    @Log(title = "评学信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTechEvaluate sysTechEvaluate)
    {
        List<SysTechEvaluate> list = sysTechEvaluateService.selectSysTechEvaluateList(sysTechEvaluate);
        ExcelUtil<SysTechEvaluate> util = new ExcelUtil<SysTechEvaluate>(SysTechEvaluate.class);
        util.exportExcel(response, list, "评学信息数据");
    }

    /**
     * 获取评学信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysTechEvaluateService.selectSysTechEvaluateById(id));
    }

    /**
     * 新增评学信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:add')")
    @Log(title = "评学信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTechEvaluate sysTechEvaluate)
    {
        return toAjax(sysTechEvaluateService.insertSysTechEvaluate(sysTechEvaluate));
    }

    /**
     * 修改评学信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:edit')")
    @Log(title = "评学信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTechEvaluate sysTechEvaluate)
    {
        return toAjax(sysTechEvaluateService.updateSysTechEvaluate(sysTechEvaluate));
    }

    /**
     * 删除评学信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:remove')")
    @Log(title = "评学信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysTechEvaluateService.deleteSysTechEvaluateByIds(ids));
    }

    /**
     * 查看教员员是否提交评学
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:query')")
    @GetMapping(value = "/queryEvaluate/{classId}")
    public AjaxResult queryEvaluateByUserId(@PathVariable("classId") Long classId){
        return success(sysTechEvaluateService.queryTechEvaluateByUserId(getUserId(),classId));
    }

    /**
     * 查看分期评学分析结果
     */
    @PreAuthorize("@ss.hasPermi('teach:techevaluate:query')")
    @GetMapping(value = "/getEvaluateResult/{classId}")
    public AjaxResult getEvaluateResult(@PathVariable Long classId){
        return success(sysTechEvaluateService.getTechEvaluateResult(classId));
    }
}
