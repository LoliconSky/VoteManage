package com.bfchengnuo.po;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class Users implements Serializable {
    private int id;
    private String name;
    private String password;
    private Collection<UserChoose> userChoosesById;

    public Users() {
    }

    public Users(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(name, users.name) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password);
    }

    public Collection<UserChoose> getUserChoosesById() {
        return userChoosesById;
    }

    public void setUserChoosesById(Collection<UserChoose> userChoosesById) {
        this.userChoosesById = userChoosesById;
    }
}
