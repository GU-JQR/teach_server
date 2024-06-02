package com.ruoyi.teach.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysLabelMapper;
import com.ruoyi.common.core.domain.entity.SysLabel;
import com.ruoyi.teach.service.ISysLabelService;
import org.springframework.util.ObjectUtils;

/**
 * 标签信息Service业务层处理
 *
 * @author sqc
 * @date 2024-02-28
 */
@Service
public class SysLabelServiceImpl implements ISysLabelService
{
    @Autowired
    private SysLabelMapper sysLabelMapper;

    /**
     * 查询标签信息
     *
     * @param labelId 标签信息主键
     * @return 标签信息
     */
    @Override
    public SysLabel selectSysLabelByLabelId(Long labelId)
    {
        return sysLabelMapper.selectSysLabelByLabelId(labelId);
    }

    /**
     * 查询标签信息列表
     *
     * @param sysLabel 标签信息
     * @return 标签信息
     */
    @Override
    public List<SysLabel> selectSysLabelList(SysLabel sysLabel)
    {
        return sysLabelMapper.selectSysLabelList(sysLabel);
    }

    /**
     * 新增标签信息
     *
     * @param sysLabel 标签信息
     * @return 结果
     */
    @Override
    public int insertSysLabel(SysLabel sysLabel)
    {
        SysLabel label = sysLabelMapper.selectSysLabelByLabelId(sysLabel.getParentId());
        if(StringUtils.isNotNull(label)){
            // 如果父节点不为正常状态,则不允许新增子节点
            if (!UserConstants.LABEL_NORMAL.equals(label.getStatus()))
            {
                throw new ServiceException("标签停用，不允许新增");
            }
            sysLabel.setAncestors(label.getAncestors()+","+sysLabel.getParentId());
            sysLabel.setParentName(label.getLabelName());
        }
        sysLabel.setCreateTime(DateUtils.getNowDate());
        return sysLabelMapper.insertSysLabel(sysLabel);
    }

    /**
     * 修改标签信息
     *
     * @param sysLabel 标签信息
     * @return 结果
     */
    @Override
    public int updateSysLabel(SysLabel sysLabel)
    {
        SysLabel newParentLabel = sysLabelMapper.selectSysLabelByLabelId(sysLabel.getParentId());
        SysLabel oldLabel = sysLabelMapper.selectSysLabelByLabelId(sysLabel.getLabelId());
        if(StringUtils.isNotNull(newParentLabel) && StringUtils.isNotNull(oldLabel)){
            String newAncestors = newParentLabel.getAncestors() + "," + newParentLabel.getLabelId();
            String oldAncestors = oldLabel.getAncestors();
            sysLabel.setAncestors(newAncestors);
            updateLabelChildren(sysLabel.getLabelId(), newAncestors, oldAncestors);
            sysLabel.setParentName(newParentLabel.getLabelName());
        }
        sysLabel.setUpdateTime(DateUtils.getNowDate());
        int result = sysLabelMapper.updateSysLabel(sysLabel);
        if (UserConstants.LABEL_NORMAL.equals(sysLabel.getStatus()) && StringUtils.isNotEmpty(sysLabel.getAncestors())
                && !StringUtils.equals("0", sysLabel.getAncestors()))
        {
            // 如果该标签是启用状态，则启用该标签的所有上级部门
            updateParentLabelStatusNormal(sysLabel);
        }
        //如果改标签为父级标签，同步所有子级标签parentName
        sysLabelMapper.updateParentNameByParentId(sysLabel);
        return result;
    }

    /**
     * 修改该标签的父级标签状态
     *
     * @param sysLabel 当前标签
     */
    private void updateParentLabelStatusNormal(SysLabel sysLabel){
        String ancestors = sysLabel.getAncestors();
        Long[] deptIds = Convert.toLongArray(ancestors);
        sysLabelMapper.updateLabelStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     *
     * @param labelId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateLabelChildren(Long labelId, String newAncestors, String oldAncestors)
    {
        List<SysLabel> children = sysLabelMapper.selectChildrenLabelById(labelId);
        for (SysLabel child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            sysLabelMapper.updateLabelChildren(children);
        }
    }

    /**
     * 批量删除标签信息
     *
     * @param labelIds 需要删除的标签信息主键
     * @return 结果
     */
    @Override
    public int deleteSysLabelByLabelIds(Long[] labelIds)
    {

        return sysLabelMapper.deleteSysLabelByLabelIds(labelIds);
    }




    /**
     * 删除标签信息信息
     *
     * @param labelId 标签信息主键
     * @return 结果
     */
    @Override
    public int deleteSysLabelByLabelId(Long labelId)
    {
        return sysLabelMapper.deleteSysLabelByLabelId(labelId);
    }



    /**
     * 查询全部标签树结构信息
     *
     * @param label 部门信息
     * @return 标签树信息集合
     */
    @Override
    public List<TreeSelect> selectLabelTreeList(SysLabel label){
        List<SysLabel> labels = sysLabelMapper.selectSysLabelList(label);
        return buildLabelTreeSelect(labels);
    }

    @Override
    public List<TreeSelect> labelAncestorNameList(String[] splitAncestors) {

        List<SysLabel> labelList = sysLabelMapper.labelAncestorNameList(splitAncestors);
        return buildLabelTreeSelect(labelList);
    }

//    判断是否存在子菜单

    @Override
    public Boolean hasChildByLabelId(Long[] labelIds) {
        for (Long labelId : labelIds) {
            List<SysLabel> sysLabels = sysLabelMapper.hasChildByLabelId(labelId);
            if(ObjectUtils.isEmpty(sysLabels))
                return true;
        }
        return false;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param labels 标签列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildLabelTreeSelect(List<SysLabel> labels)
    {
        List<SysLabel> labelTrees = buildLabelTree(labels);
        return labelTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param labels 标签列表
     * @return 树结构列表
     */
    @Override
    public List<SysLabel> buildLabelTree(List<SysLabel> labels)
    {
        List<SysLabel> returnList = new ArrayList<SysLabel>();
        List<Long> tempList = labels.stream().map(SysLabel::getLabelId).collect(Collectors.toList());
        for (SysLabel label : labels)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(label.getParentId()))
            {
                recursionFn(labels, label);
                returnList.add(label);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = labels;
        }
        return returnList;
    }




    /**
     * 递归列表
     */
    private void recursionFn(List<SysLabel> list, SysLabel t)
    {
        // 得到子节点列表
        List<SysLabel> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysLabel tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysLabel> getChildList(List<SysLabel> list, SysLabel t)
    {
        List<SysLabel> tlist = new ArrayList<SysLabel>();
        Iterator<SysLabel> it = list.iterator();
        while (it.hasNext())
        {
            SysLabel n = (SysLabel) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getLabelId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysLabel> list, SysLabel t)
    {
        return getChildList(list, t).size() > 0;
    }
}
