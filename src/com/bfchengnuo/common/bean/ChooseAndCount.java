package com.bfchengnuo.common.bean;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class ChooseAndCount {
    private int id;
    private String conte;
    private Object cSum;

    public ChooseAndCount() {
    }

    public ChooseAndCount(int id, String conte, Object cSum) {
        this.id = id;
        this.conte = conte;
        this.cSum = cSum;
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

    public Object getcSum() {
        return cSum;
    }

    public void setcSum(Object cSum) {
        this.cSum = cSum;
    }

    @Override
    public String toString() {
        return "ChooseAndCount{" +
                "id=" + id +
                ", conte='" + conte + '\'' +
                ", cSum=" + cSum +
                '}';
    }
}
