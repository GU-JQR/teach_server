package com.ruoyi.teach.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class ClassTreeVO {

    private Long id;

    private String label;

    private List<ClassTreeVO> children;

    public ClassTreeVO(Long id,String label){
        this.id = id;
        this.label = label;
        this.children = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ClassTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<ClassTreeVO> children) {
        this.children = children;
    }
}
