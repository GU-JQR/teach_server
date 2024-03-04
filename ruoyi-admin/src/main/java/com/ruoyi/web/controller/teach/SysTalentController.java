package com.ruoyi.web.controller.teach;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.domain.entity.SysLabel;
import com.ruoyi.teach.domain.SysTalent;
import com.ruoyi.teach.service.ISysLabelService;
import com.ruoyi.teach.service.ISysTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 人才管理Controller
 *
 * @author ruoyi
 * @date 2024-03-02
 */
@RestController
@RequestMapping("/teach/talent")
public class SysTalentController extends BaseController {
    @Autowired
    private ISysTalentService sysTalentService;


    @Autowired
    private ISysLabelService sysLabelService;



    /**
     * 查询人才管理列表
     * 支持模糊查询 标签 content
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTalent sysTalent) {

//        logger.info("sysTalent====={}",sysTalent);
        //分页
        startPage();
        List<SysTalent> list = sysTalentService.selectSysTalentList(sysTalent);
        return getDataTable(list);
    }

    /**
     * 导出人才管理列表
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:export')")
    @Log(title = "人才管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTalent sysTalent) {
        List<SysTalent> list = sysTalentService.selectSysTalentList(sysTalent);
        ExcelUtil<SysTalent> util = new ExcelUtil<SysTalent>(SysTalent.class);
        util.exportExcel(response, list, "人才管理数据");
    }

    /**
     * 获取人才管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysTalentService.selectSysTalentById(id));
    }

    /**
     * 新增人才管理
     * 通过查询 增添加labelName
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:add')")
    @Log(title = "人才管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTalent sysTalent) {
        /**
         * 通过查询labelName 然后再弄到sysTalent中
         */
        SysLabel sysLabel = sysLabelService.selectSysLabelByLabelId(Long.valueOf(sysTalent.getLabelId()));
        if(sysLabel!=null){
            sysTalent.setLabelName(sysLabel.getLabelName());
        }

        return toAjax(sysTalentService.insertSysTalent(sysTalent));
    }

    /**
     * 修改人才管理
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:edit')")
    @Log(title = "人才管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTalent sysTalent) {

        /**
         * 通过查询labelName 然后再set到sysTalent中
         */

        SysLabel sysLabel = sysLabelService.selectSysLabelByLabelId(Long.valueOf(sysTalent.getLabelId()));
        if(sysLabel!=null){
            sysTalent.setLabelName(sysLabel.getLabelName());

        }

        return toAjax(sysTalentService.updateSysTalent(sysTalent));
    }

    /**
     * 删除人才管理
     */
    @PreAuthorize("@ss.hasPermi('teach:talent:remove')")
    @Log(title = "人才管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysTalentService.deleteSysTalentByIds(ids));
    }
}
