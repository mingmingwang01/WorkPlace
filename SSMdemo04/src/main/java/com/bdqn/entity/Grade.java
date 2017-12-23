package com.bdqn.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/9/009.
 */
public class Grade {
    /*
     id int(11) NOT NULL        班级id
    grade_name varchar(10) NULL 班级名称
    create_date date NULL       创建时间
    details varchar(500) NULL   班级描述
    */
    private Integer id;
    private String gradeName;
    private String details;

    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeName='" + gradeName + '\'' +
                ", details='" + details + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
