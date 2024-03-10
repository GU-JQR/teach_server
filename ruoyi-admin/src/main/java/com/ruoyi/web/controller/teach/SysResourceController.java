package com.ruoyi.web.controller.teach;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysLabel;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.teach.domain.SysResource;
import com.ruoyi.teach.service.ISysLabelService;
import com.ruoyi.teach.service.ISysResourceService;
import com.ruoyi.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资源库Controller
 *
 * @author ruoyi
 * @date 2024-03-03
 */
@RestController
@RequestMapping("/teach/resource")
public class SysResourceController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private ISysResourceService sysResourceService;

    @Autowired
    private ISysLabelService sysLabelService;

    /**
     * 查询资源库列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysResource sysResource) {
        startPage();
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        return getDataTable(list);
    }

    /**
     * 导出资源库列表
     */
    @PreAuthorize("@ss.hasPermi('teach:resource:export')")
    @Log(title = "资源库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysResource sysResource) {
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        ExcelUtil<SysResource> util = new ExcelUtil<SysResource>(SysResource.class);
        util.exportExcel(response, list, "资源库数据");
    }

    /**
     * 获取资源库详细信息
     */
    @PreAuthorize("@ss.hasPermi('teach:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysResourceService.selectSysResourceById(id));
    }

    /**
     * 新增资源库
     * 首先知道他是哪个库的二级标签
     * 其次要去展示他的最底层的labelId
     */
    @PreAuthorize("@ss.hasPermi('teach:resource:add')")
    @Log(title = "资源库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysResource sysResource) {
        //=======通过前端传递过来的labelId将字段typeId和typeName（二级标签）保存=====
        //将labelId 最底层的标签给set进去
        SysLabel sysLabel = sysLabelService.selectSysLabelByLabelId(sysResource.getLabelId());
        sysResource.setLabelId(sysResource.getLabelId());
        sysResource.setTypeName(sysLabel.getLabelName());
        //============处理fileType后缀===========
        String addressFile = sysResource.getAddressFile();
        String[] splitList = addressFile.split("/");
        String last = splitList[splitList.length - 1];
        //防止用户文件名有多个 "."
        String[] suffixList = last.split("\\.");
        String suffix = suffixList[suffixList.length - 1];
        sysResource.setFileType(suffix);
        return toAjax(sysResourceService.insertSysResource(sysResource));
    }

    /**
     * 修改资源库
     * 首先知道他是哪个库的二级标签
     * 其次要去展示他的最底层的labelId
     */
    @PreAuthorize("@ss.hasPermi('teach:resource:edit')")
    @Log(title = "资源库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysResource sysResource) {
        //找到type二级标签
        SysLabel sysLabel = sysLabelService.selectSysLabelByLabelId(sysResource.getLabelId());
        //将labelId 最底层的标签给set进去
        sysResource.setLabelId(sysResource.getLabelId());
        sysResource.setTypeName(sysLabel.getLabelName());
        //============处理fileType后缀===========
        String addressFile = sysResource.getAddressFile();
        String[] splitList = addressFile.split("/");
        String last = splitList[splitList.length - 1];
        //防止用户文件名有多个 "."
        String[] suffixList = last.split("\\.");
        String suffix = suffixList[suffixList.length - 1];
        sysResource.setFileType(suffix);
        return toAjax(sysResourceService.updateSysResource(sysResource));
    }

    /**
     * 删除资源库
     */
    @PreAuthorize("@ss.hasPermi('teach:resource:remove')")
    @Log(title = "资源库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysResourceService.deleteSysResourceByIds(ids));
    }

    /**
     * 文件下载
     */
    @GetMapping("/download")
    public void downloadFile(String filepath, HttpServletResponse response, HttpServletRequest request) {
        String prefix = "/profile/upload";
        try {
            String[] split = filepath.split("/");
            String realFileName = split[split.length - 1];
            String filePath = RuoYiConfig.getUploadPath() + filepath;
            String realPath = filePath.replace(prefix, "");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(realPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }

    }
    /**
     * @description:根据类别统计资源个数
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    @GetMapping("/count")
    public AjaxResult countByCategoryType(){
        long[] list= sysResourceService.countByCategoryType();
        return AjaxResult.success(list);
    }
    @GetMapping("/getUpdateDate")
    public AjaxResult getUpdateDate(){
        double[] updateRate = sysResourceService.getUpdateDate();
        return  AjaxResult.success(updateRate);
    }
}
