package com.bfchengnuo.service;

import com.bfchengnuo.dao.Dao;
import com.bfchengnuo.dao.DaoImpl;
import com.bfchengnuo.po.Users;

/**
 * Created by 冰封承諾Andy on 2018/5/26.
 */
public class LoginService {
    private Dao<Users> dao = new DaoImpl<>();

    public Users queryUserByName(String name){
        return dao.queryByName(Users.class, name);
    }

    public void addUser(Users user){
        dao.add(user);
    }
}
