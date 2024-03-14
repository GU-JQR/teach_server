package com.ruoyi.web.controller.teach;

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.teach.service.ISysClassService;
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
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.service.ISysStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学员信息Controller
 *
 * @author sqc
 * @date 2024-03-03
 */
@RestController
@RequestMapping("/teach/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ISysStudentService sysStudentService;

    @Autowired
    private ISysClassService sysClassService;
    /**
     * 查询学员信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent sysStudent)
    {
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }

    /**
     * 查询全部学员信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:student:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(SysStudent sysStudent)
    {
        return success(sysStudentService.selectSysStudentListAll(sysStudent));
    }

    /**
     * 导出学员信息列表
     */
    @PreAuthorize("@ss.hasPermi('teach:student:export')")
    @Log(title = "学员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStudent sysStudent)
    {
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        util.exportExcel(response, list, "学员信息数据");
    }

    /**
     * 获取学员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysStudentService.selectSysStudentById(id));
    }

    /**
     * 获取学员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:student:query')")
    @GetMapping(value = "/getByUserId")
    public AjaxResult getByUserId()
    {
        return success(sysStudentService.selectSysStudentByUserId(getUserId()));
    }

    /**
     * 新增学员信息
     */
    @PreAuthorize("@ss.hasPermi('teach:student:add')")
    @Log(title = "学员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.insertSysStudent(sysStudent));
    }

    /**
     * 修改学员信息
     */
    @PreAuthorize("@ss.hasPermi('teach:student:edit')")
    @Log(title = "学员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    /**
     * 删除学员信息
     */
    @PreAuthorize("@ss.hasPermi('teach:student:remove')")
    @Log(title = "学员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysStudentService.deleteSysStudentByIds(ids));
    }

    /**
     * 当前期和上一期学员成绩数据
     */
    @PreAuthorize("@ss.hasPermi('teach:student:query')")
    @GetMapping(value = "/stuInfo/{classId}")
    public AjaxResult stuInfo(@PathVariable("classId") Long classId)
    {
        return success(sysStudentService.selectSysStudentByClassId(classId));
    }
    //查询最新一期的学员ClassId
    @PreAuthorize("@ss.hasPermi('teach:student:query')")
    @GetMapping(value = "/getNowClass")
    public AjaxResult getNowClass()
    {
        return success(sysClassService.selectNowClassId().getId());
    }
    //查找表信息
    @PreAuthorize("@ss.hasPermi('teach:student:query')")
    @GetMapping(value = "/getChartInfo/{id}")
    public AjaxResult getChartInfo(@PathVariable Long id )
    {
        return sysClassService.selectChartInfoById(id);
    }

//    批量导入
    @PreAuthorize("@ss.hasPermi('teach:student:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file,Long classId) throws Exception
    {
        ExcelUtil<SysStudent> util = new ExcelUtil<>(SysStudent.class);
        InputStream inputStream = file.getInputStream();
        List<SysStudent> studentList = util.importExcel(inputStream);
        String message = sysStudentService.importStudent(studentList,classId);
        return success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<SysStudent> util = new ExcelUtil<>(SysStudent.class);
        util.importTemplateExcel(response, "用户数据");
    }



}
