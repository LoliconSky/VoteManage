package com.bfchengnuo.po;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class Topic {
    private int id;
    private String conte;
    private Collection<Choose> choosesById;

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
        Topic topic = (Topic) o;
        return id == topic.id &&
                Objects.equals(conte, topic.conte);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, conte);
    }

    public Collection<Choose> getChoosesById() {
        return choosesById;
    }

    public void setChoosesById(Collection<Choose> choosesById) {
        this.choosesById = choosesById;
    }
}
