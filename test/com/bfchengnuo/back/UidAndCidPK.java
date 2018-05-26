package com.bfchengnuo.back;

import java.io.Serializable;
import java.util.Objects;

/**
 * 联合主键类
 * Created by 冰封承諾Andy on 2018/5/26.
 */
public class UidAndCidPK implements Serializable {
    private int cid;
    private int uid;

    public UidAndCidPK() {
    }

    public UidAndCidPK(int cid, int uid) {
        this.cid = cid;
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UidAndCidPK that = (UidAndCidPK) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, uid);
    }
}
