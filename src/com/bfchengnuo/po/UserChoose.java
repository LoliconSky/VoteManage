package com.bfchengnuo.po;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class UserChoose {
    private int id;
    private Timestamp subdate;
    private Users usersByUid;
    private Choose chooseByCid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSubdate() {
        return subdate;
    }

    public void setSubdate(Timestamp subdate) {
        this.subdate = subdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChoose that = (UserChoose) o;
        return id == that.id &&
                Objects.equals(subdate, that.subdate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subdate);
    }

    public Users getUsersByUid() {
        return usersByUid;
    }

    public void setUsersByUid(Users usersByUid) {
        this.usersByUid = usersByUid;
    }

    public Choose getChooseByCid() {
        return chooseByCid;
    }

    public void setChooseByCid(Choose chooseByCid) {
        this.chooseByCid = chooseByCid;
    }
}
