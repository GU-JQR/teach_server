package com.ruoyi.teach.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.teach.domain.SysClass;
import com.ruoyi.teach.mapper.SysClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.teach.domain.SysEducation;
import com.ruoyi.teach.mapper.SysStudentMapper;
import com.ruoyi.teach.domain.SysStudent;
import com.ruoyi.teach.service.ISysStudentService;

/**
 * 学员信息Service业务层处理
 *
 * @author sqc
 * @date 2024-03-03
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService
{
    @Autowired
    private SysStudentMapper sysStudentMapper;

    @Autowired
    private SysClassMapper sysClassMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    @Override
    public SysStudent selectSysStudentById(Long id)
    {
        return sysStudentMapper.selectSysStudentById(id);
    }

    /**
     * 查询学员信息
     *
     * @param userId 学员信息主键
     * @return 学员信息
     */
    @Override
    public SysStudent selectSysStudentByUserId(Long userId)
    {
        SysStudent student = sysStudentMapper.selectSysStudentByUserId(userId);
        return student;
    }

    /**
     * 查询学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 查询全部学员信息列表
     *
     * @param sysStudent 学员信息
     * @return 学员信息集合
     */
    @Override
    public List<SysStudent> selectSysStudentListAll(SysStudent sysStudent){
        return sysStudentMapper.selectSysStudentListAll(sysStudent);
    }

    /**
     * 新增学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSysStudent(SysStudent sysStudent)
    {
        SysClass sysClass = sysClassMapper.selectSysClassById(sysStudent.getClassId());
        sysStudent.setCreateTime(DateUtils.getNowDate());
        sysStudent.setNumber(sysClass.getNumber()*1000L+sysStudentMapper.countSysStudentByClassId(sysStudent.getClassId())+1);
        int rows = sysStudentMapper.insertSysStudent(sysStudent);
        insertSysEducation(sysStudent);
        //新建用户
        SysDept dept = sysDeptMapper.selectDeptById(sysClass.getDeptId());
        SysUser user = new SysUser();
        user.setDeptId(dept.getDeptId());
        user.setUserName(sysStudent.getNumber().toString());
        user.setNickName(sysStudent.getName());
        user.setPhonenumber(sysStudent.getPhone());
        user.setEmail(sysStudent.getEmail());
        user.setAvatar(sysStudent.getAvatar());
        user.setPassword(SecurityUtils.encryptPassword(user.getPhonenumber()));
        sysUserMapper.insertUser(user);
        //关联学生角色
        List<SysUserRole> userRoles = new ArrayList<>();
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(2L);
        userRole.setUserId(user.getUserId());
        userRoles.add(userRole);
        sysUserRoleMapper.batchUserRole(userRoles);
        //关联学员岗位
        List<SysUserPost> userPosts = new ArrayList<>();
        SysUserPost userPost = new SysUserPost();
        userPost.setPostId(3L);
        userPost.setUserId(user.getUserId());
        userPosts.add(userPost);
        sysUserPostMapper.batchUserPost(userPosts);
        return rows;
    }

    /**
     * 修改学员信息
     *
     * @param sysStudent 学员信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        sysStudent.setUpdateTime(DateUtils.getNowDate());
        sysStudentMapper.deleteSysEducationByStudentId(sysStudent.getId());
        insertSysEducation(sysStudent);
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysStudentByIds(Long[] ids)
    {
        sysStudentMapper.deleteSysEducationByStudentIds(ids);
        return sysStudentMapper.deleteSysStudentByIds(ids);
    }

    /**
     * 删除学员信息信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysStudentById(Long id)
    {
        sysStudentMapper.deleteSysEducationByStudentId(id);
        return sysStudentMapper.deleteSysStudentById(id);
    }

    @Override
    public AjaxResult selectSysStudentByClassId(Long classId) {
        //查询优良信息
        SysStudent sysStudent= sysStudentMapper.selectSysStudentByClassId(classId);
        //查询当前期数学生的情况
        //将信息封装成map
        AjaxResult ajax = AjaxResult.success();
        HashMap<String, Integer> mapNow = new HashMap<>();
        ajax.put("nowClass",handle(sysStudent));
        //根据classId查找number
        SysClass sysClass = sysClassMapper.selectSysClassById(classId);
        SysClass sysClassLast = sysClassMapper.selectLastSysClassByNumber(Long.valueOf(sysClass.getNumber()));
        if(sysClassLast == null){
            ajax.put("lastClass",null);
            return ajax;
        }
        SysStudent sysStudentLast = sysStudentMapper.selectSysStudentByClassId(sysClassLast.getId());
        if(sysClassLast == null){
            ajax.put("lastClass",null);
            return ajax;
        }
        ajax.put("lastClass",handle(sysStudentLast));
        return ajax;
    }
    private Map<String,Integer> handle(SysStudent sysStudent){
        HashMap<String, Integer> mapNow = new HashMap<>();
        mapNow.put("level3",sysStudent.getLevel3());
        mapNow.put("level2",sysStudent.getLevel2());
        mapNow.put("level1",sysStudent.getLevel1());
        mapNow.put("level0",sysStudent.getLevel0());
        mapNow.put("enhanceCount",sysStudent.getEnhanceCount());
        mapNow.put("classStuCount",sysStudent.getClassStuCount());
        return mapNow;
    }
    /**
     * 新增教育经历信息信息
     *
     * @param sysStudent 学员信息对象
     */
    public void insertSysEducation(SysStudent sysStudent)
    {
        List<SysEducation> sysEducationList = sysStudent.getSysEducationList();
        Long id = sysStudent.getId();
        if (StringUtils.isNotNull(sysEducationList))
        {
            List<SysEducation> list = new ArrayList<SysEducation>();
            for (SysEducation sysEducation : sysEducationList)
            {
                sysEducation.setStudentId(id);
                list.add(sysEducation);
            }
            if (list.size() > 0)
            {
                sysStudentMapper.batchSysEducation(list);
            }
        }
    }
}
