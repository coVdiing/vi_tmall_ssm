package com.vi.tmall.pojo;

import java.util.Date;

public class Review {
    private Integer id;

    private String content;

    private Integer uid;

    private Integer pid;

    private Date creaeteDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreaeteDate() {
        return creaeteDate;
    }

    public void setCreaeteDate(Date creaeteDate) {
        this.creaeteDate = creaeteDate;
    }
}