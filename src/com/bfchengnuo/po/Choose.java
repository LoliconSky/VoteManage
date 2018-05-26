package com.bfchengnuo.po;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class Choose implements Serializable {
    private int id;
    private String conte;
    private Topic topicByTid;
    private Collection<UserChoose> userChoosesById;

    public Choose() {
    }

    public Choose(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConte() {
        return conte;
    }

    public void setConte(String conte) {
        this.conte = conte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choose choose = (Choose) o;
        return id == choose.id &&
                Objects.equals(conte, choose.conte);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, conte);
    }

    public Topic getTopicByTid() {
        return topicByTid;
    }

    public void setTopicByTid(Topic topicByTid) {
        this.topicByTid = topicByTid;
    }

    public Collection<UserChoose> getUserChoosesById() {
        return userChoosesById;
    }

    public void setUserChoosesById(Collection<UserChoose> userChoosesById) {
        this.userChoosesById = userChoosesById;
    }
}
