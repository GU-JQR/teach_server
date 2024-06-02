package com.ruoyi.teach.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysTechQuestion;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.teach.mapper.SysTechQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teach.mapper.SysTechEvaluateMapper;
import com.ruoyi.system.domain.SysTechEvaluate;
import com.ruoyi.system.service.ISysTechEvaluateService;

/**
 * 评学信息Service业务层处理
 * 
 * @author sqc
 * @date 2024-04-05
 */
@Service
public class SysTechEvaluateServiceImpl implements ISysTechEvaluateService 
{
    @Autowired
    private SysTechEvaluateMapper sysTechEvaluateMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysTechQuestionMapper sysTechQuestionMapper;

    /**
     * 查询评学信息
     * 
     * @param id 评学信息主键
     * @return 评学信息
     */
    @Override
    public SysTechEvaluate selectSysTechEvaluateById(Long id)
    {
        return sysTechEvaluateMapper.selectSysTechEvaluateById(id);
    }

    /**
     * 查询评学信息列表
     * 
     * @param sysTechEvaluate 评学信息
     * @return 评学信息
     */
    @Override
    public List<SysTechEvaluate> selectSysTechEvaluateList(SysTechEvaluate sysTechEvaluate)
    {
        return sysTechEvaluateMapper.selectSysTechEvaluateList(sysTechEvaluate);
    }

    /**
     * 新增评学信息
     * 
     * @param sysTechEvaluate 评学信息
     * @return 结果
     */
    @Override
    public int insertSysTechEvaluate(SysTechEvaluate sysTechEvaluate)
    {
        SysTechEvaluate evaluate = new SysTechEvaluate();
        evaluate.setClassId(sysTechEvaluate.getClassId());
        evaluate.setUserId(sysTechEvaluate.getUserId());
        List<SysTechEvaluate> list = sysTechEvaluateMapper.selectSysTechEvaluateList(evaluate);
        if(list.size()>0){
            sysTechEvaluate.setId(list.get(0).getId());
            return sysTechEvaluateMapper.updateSysTechEvaluate(sysTechEvaluate);
        }else{
            sysTechEvaluate.setCreateTime(DateUtils.getNowDate());
            return sysTechEvaluateMapper.insertSysTechEvaluate(sysTechEvaluate);
        }
    }

    /**
     * 修改评学信息
     * 
     * @param sysTechEvaluate 评学信息
     * @return 结果
     */
    @Override
    public int updateSysTechEvaluate(SysTechEvaluate sysTechEvaluate)
    {
        sysTechEvaluate.setUpdateTime(DateUtils.getNowDate());
        return sysTechEvaluateMapper.updateSysTechEvaluate(sysTechEvaluate);
    }

    /**
     * 批量删除评学信息
     * 
     * @param ids 需要删除的评学信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTechEvaluateByIds(Long[] ids)
    {
        return sysTechEvaluateMapper.deleteSysTechEvaluateByIds(ids);
    }

    /**
     * 删除评学信息信息
     * 
     * @param id 评学信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTechEvaluateById(Long id)
    {
        return sysTechEvaluateMapper.deleteSysTechEvaluateById(id);
    }

    /**
     * 查看教员是否提交评学
     * @param userId
     * @return
     */
    @Override
    public boolean queryTechEvaluateByUserId(Long userId,Long classId){
        return sysTechEvaluateMapper.queryTechEvaluateByUserId(userId,classId) > 0;
    }

    /**
     * 查看分期评学分析结果
     * @param classId
     * @return
     */
    @Override
    public Map<String,Object> getTechEvaluateResult(Long classId){
        Map<String, Object> map = new HashMap<>();
        List<SysDictData> sysQuestionType = sysDictDataMapper.selectDictDataByType("sys_question_type");
        List<SysTechEvaluate> list = sysTechEvaluateMapper.selectSysTechEvaluateListByClassId(classId);
        List<SysTechQuestion> questions = sysTechQuestionMapper.selectSysTechQuestionListByClassId(classId);
        Map<String, Map<String,Object>> arrayMap = new HashMap<>(); //用来映射每个type的数组
        int total = list.size();//评教总数
        int score_total = 0;
        int total_0_20 = 0;
        int total_20_40 = 0;
        int total_40_60 = 0;
        int total_60_80 = 0;
        int total_80_100 = 0;
        //初始化数组
        for (SysDictData sysDictData : sysQuestionType) {
            Map<String, Object> tmp = new HashMap<>();
            List<Integer> arrayList = new ArrayList<>();
            List<String> nameList = new ArrayList<>();
            tmp.put("arrayList", arrayList);
            tmp.put("nameList", nameList);
            arrayMap.put(String.valueOf(sysDictData.getDictValue()), tmp);
        }
        if (StringUtils.isNotNull(list)) {
            //先将每一个type的question信息提前取出来 然后根据
            for (SysTechEvaluate item : list) {
                int score = item.getTotal();
                score_total += score;
                if (0 <= score && score <= 20) {
                    total_0_20 += 1;
                } else if (20 < score && score <= 40) {
                    total_20_40 += 1;
                } else if (40 < score && score <= 60) {
                    total_40_60 += 1;
                } else if (60 < score && score <= 80) {
                    total_60_80 += 1;
                } else if (80 < score && score <= 100) {
                    total_80_100 += 1;
                }
                JSONObject result = JSON.parseObject(item.getResult());
                Integer idx = 0;
                String lastType = "";
                for (Map.Entry<String, Object> entry : result.entrySet()) { //枚举每一个题目
                    Optional<SysTechQuestion> question = questions
                            .stream()
                            .filter(k -> k.getId().toString().equals(entry.getKey()))
                            .findFirst();
                    //根据当前题目对应的类型取出数组然后统计
                    Map<String, Object> typeMap = arrayMap.get(question.get().getQuestionType());
                    List<Integer> score_array = (List<Integer>) typeMap.get("arrayList");
                    List<String>  nameList = (List<String>)typeMap.get("nameList");
                    //根据当前题目对应的questionType来取得所有questiontype种对应的列表
                    List<SysTechQuestion> collect =
                            questions.stream().filter(k -> k.getQuestionType().equals(question.get().getQuestionType())).collect(Collectors.toList());
                    if(nameList.size()<collect.size()){
                        nameList.add(question.get().getQuestion());
                    }
                    Integer i = 0;
                    boolean indexOut = true; // 成绩有可能是 0 分 所以不能用是否为 0 来判断越界没有
                    if(!question.get().getQuestionType().equals(lastType)){
                        idx = 0;
                        lastType = question.get().getQuestionType();
                    }
                    if (idx < score_array.size()) {
                        indexOut = false;
                        i = score_array.get(idx);
                    }
                    switch (Integer.parseInt(String.valueOf(entry.getValue()))) {
                        case 1:
                            //set不会自动创建新的元素
                            if (indexOut) {
                                score_array.add(question.get().getScoreOne());
                            } else {
                                score_array.set(idx, i + question.get().getScoreOne());
                            }
                            break;
                        case 2:
                            if (indexOut) {
                                score_array.add(question.get().getScoreTwo());
                            } else {
                                score_array.set(idx, i + question.get().getScoreTwo());
                            }
                            break;
                        case 3:
                            if (indexOut) {
                                score_array.add(question.get().getScoreThree());
                            } else {
                                score_array.set(idx, i + question.get().getScoreThree());
                            }
                            break;
                        case 4:
                            if (indexOut) {
                                score_array.add(question.get().getScoreFour());
                            } else {
                                score_array.set(idx, i + question.get().getScoreFour());
                            }
                            break;
                    }
                    idx++;
                }
            }
        }
        //处理成绩和名字
        for(SysDictData sysDictData:sysQuestionType){
            Map<String,Object> tmp = arrayMap.get(String.valueOf(sysDictData.getDictValue()));
            List<Integer> arrayList = (List<Integer>) tmp.get("arrayList");
            List<Float> floats = new ArrayList<>();
            for (Integer integer : arrayList) {
                floats.add((float) integer / total);
            }
            tmp.put("arrayList", floats);
            arrayMap.put(String.valueOf(sysDictData.getDictValue()), tmp);
        }
        map.put("total", total);
        map.put("score", (float) score_total / total);
        map.put("total_0_20", total_0_20);
        map.put("total_20_40", total_20_40);
        map.put("total_40_60", total_40_60);
        map.put("total_60_80", total_60_80);
        map.put("total_80_100", total_80_100);
        map.put("result", arrayMap);
        return map;
    }
}
