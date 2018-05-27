package com.bfchengnuo.common.bean;

/**
 * 用户选择的某个题目的选项
 * Created by 冰封承諾Andy on 2018/5/26.
 */
public class UserTopicChoose {
    private Integer id;
    private Integer tid;
    private Integer cid;

    public UserTopicChoose(Integer id, Integer tid, Integer cid) {
        this.id = id;
        this.tid = tid;
        this.cid = cid;
    }

    public UserTopicChoose() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "UserTopicChoose{" +
                "id=" + id +
                ", tid=" + tid +
                ", cid=" + cid +
                '}';
    }
}
