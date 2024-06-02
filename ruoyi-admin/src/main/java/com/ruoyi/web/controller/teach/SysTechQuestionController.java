package com.ruoyi.web.controller.teach;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.system.domain.SysTechQuestion;
import com.ruoyi.system.service.ISysTechQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评学题库信息Controller
 * 
 * @author sqc
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/teach/techQuestion")
public class SysTechQuestionController extends BaseController
{
    @Autowired
    private ISysTechQuestionService sysTechQuestionService;

    /**
     * 查询评学题库信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTechQuestion sysTechQuestion)
    {
        startPage();
        List<SysTechQuestion> list = sysTechQuestionService.selectSysTechQuestionList(sysTechQuestion);
        return getDataTable(list);
    }

    /**
     * 前端查询评学信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:list')")
    @GetMapping("/listFront")
    public AjaxResult listFront(SysTechQuestion sysTechQuestion)
    {
        List<SysTechQuestion> list = sysTechQuestionService.selectSysTechQuestionList(sysTechQuestion);
        if(StringUtils.isNotNull(list)){
            return success(list);
        }
        return error("未查到评教信息！");
    }

    /**
     * 导出评学题库信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:export')")
    @Log(title = "评学题库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTechQuestion sysTechQuestion)
    {
        List<SysTechQuestion> list = sysTechQuestionService.selectSysTechQuestionList(sysTechQuestion);
        ExcelUtil<SysTechQuestion> util = new ExcelUtil<SysTechQuestion>(SysTechQuestion.class);
        util.exportExcel(response, list, "评学题库信息数据");
    }

    /**
     * 获取评学题库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysTechQuestionService.selectSysTechQuestionById(id));
    }

    /**
     * 新增评学题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:add')")
    @Log(title = "评学题库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTechQuestion sysTechQuestion)
    {
        return toAjax(sysTechQuestionService.insertSysTechQuestion(sysTechQuestion));
    }

    /**
     * 修改评学题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:edit')")
    @Log(title = "评学题库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTechQuestion sysTechQuestion)
    {
        return toAjax(sysTechQuestionService.updateSysTechQuestion(sysTechQuestion));
    }

    /**
     * 删除评学题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:techquestion:remove')")
    @Log(title = "评学题库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysTechQuestionService.deleteSysTechQuestionByIds(ids));
    }
}
