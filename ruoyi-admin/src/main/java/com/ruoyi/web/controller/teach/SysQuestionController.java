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
import com.ruoyi.teach.domain.SysQuestion;
import com.ruoyi.teach.service.ISysQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评教题库信息Controller
 * 
 * @author sqc
 * @date 2024-03-09
 */
@RestController
@RequestMapping("/teach/question")
public class SysQuestionController extends BaseController
{
    @Autowired
    private ISysQuestionService sysQuestionService;

    /**
     * 查询评教题库信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysQuestion sysQuestion)
    {
        startPage();
        List<SysQuestion> list = sysQuestionService.selectSysQuestionList(sysQuestion);
        return getDataTable(list);
    }

    /**
     * 查询评教题库信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:question:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(SysQuestion sysQuestion)
    {
        return success(sysQuestionService.selectSysQuestionList(sysQuestion));
    }

    /**
     * 前端查询评教信息
     */
    @PreAuthorize("@ss.hasPermi('teach:question:list')")
    @GetMapping("/listFront")
    public AjaxResult listFront(SysQuestion sysQuestion)
    {
        List<SysQuestion> list = sysQuestionService.selectSysQuestionListFront(getUserId(),sysQuestion);
        if(StringUtils.isNotNull(list)){
            return success(list);
        }
        return error("未查到评教信息！");
    }

    /**
     * 导出评教题库信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:question:export')")
    @Log(title = "评教题库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysQuestion sysQuestion)
    {
        List<SysQuestion> list = sysQuestionService.selectSysQuestionList(sysQuestion);
        ExcelUtil<SysQuestion> util = new ExcelUtil<SysQuestion>(SysQuestion.class);
        util.exportExcel(response, list, "评教题库信息数据");
    }

    /**
     * 获取评教题库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:question:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysQuestionService.selectSysQuestionById(id));
    }

    /**
     * 新增评教题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:question:add')")
    @Log(title = "评教题库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysQuestion sysQuestion)
    {
        return toAjax(sysQuestionService.insertSysQuestion(sysQuestion));
    }

    /**
     * 修改评教题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:question:edit')")
    @Log(title = "评教题库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysQuestion sysQuestion)
    {
        return toAjax(sysQuestionService.updateSysQuestion(sysQuestion));
    }

    /**
     * 删除评教题库信息
     */
    @PreAuthorize("@ss.hasPermi('teach:question:remove')")
    @Log(title = "评教题库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysQuestionService.deleteSysQuestionByIds(ids));
    }
}
