package com.nexsys.model;

public class Category {

    private Long cid;
    private String title;

    public Category() {
    }

    public Category(Long cid, String title) {
        this.cid = cid;
        this.title = title;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
