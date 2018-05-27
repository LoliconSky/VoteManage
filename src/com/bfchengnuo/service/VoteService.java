package com.bfchengnuo.service;

import com.bfchengnuo.common.bean.UserTopicChoose;
import com.bfchengnuo.dao.Dao;
import com.bfchengnuo.dao.DaoImpl;
import com.bfchengnuo.po.Choose;
import com.bfchengnuo.po.Topic;
import com.bfchengnuo.po.UserChoose;
import com.bfchengnuo.po.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冰封承諾Andy on 2018/5/26.
 */
public class VoteService {
    private Dao<Topic> tDao = new DaoImpl<>();
    private Dao<Choose> cDao = new DaoImpl<>();
    private Dao<UserChoose> ucDao = new DaoImpl<>();


    public List<Topic> queryTopicAndChoose() {
        return tDao.queryAll(Topic.class);
    }

    public int queryChooseCount(int cid){
        Choose choose = cDao.queryById(Choose.class, cid);
        return choose.getUserChoosesById().size();
    }

    public Users queryUserById(int id){
        Dao<Users> dao = new DaoImpl<>();
        return dao.queryById(Users.class, id);
    }

    public List<Users> queryChooseUsers(int cid){
        Choose choose = cDao.queryById(Choose.class, cid);
        List<Users> list = new ArrayList<>();
        choose.getUserChoosesById().forEach(userChoose -> {
            Users user = userChoose.getUsersByUid();
            list.add(user);
        });
        return list;
    }

    public void addUserChooseList(List<UserChoose> ucs){
        ucs.forEach(ucDao::add);
    }

    public void removeAllChoose(Users user) {
        ucDao.removeChooseByUid(user.getId());
    }

    public List<UserTopicChoose> queryChooseByUid(int id) {
        return ucDao.queryUCByUid(id);
    }

    public void addTopic(Topic topic) {
        tDao.add(topic);
    }
}
