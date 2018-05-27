package com.bfchengnuo.dao;

import com.bfchengnuo.common.bean.ChooseAndCount;
import com.bfchengnuo.common.bean.UserTopicChoose;
import com.bfchengnuo.po.Choose;
import com.bfchengnuo.po.Topic;
import com.bfchengnuo.po.Users;
import com.bfchengnuo.service.VoteService;
import org.junit.Test;

import java.util.List;

/**
 * Created by 冰封承諾Andy on 2018/5/25.
 */
public class TestDao {
    public static void main(String[] args) {
        Dao<Topic> dao = new DaoImpl<>();
        List<Topic> list = dao.queryAll(Topic.class);
        list.forEach(topic -> {
            System.out.println("题目：" + topic.getConte());
            topic.getChoosesById().forEach(choose -> System.out.println(choose.getConte()));
            System.out.println(  );
        });
    }

    @Test
    public void chooseCountTest(){
        Dao<Topic> dao = new DaoImpl<>();
        List<ChooseAndCount> countList = dao.getChooseCount();
        countList.forEach(System.out::println);
    }

    @Test
    public void chooseCountTest2(){
        Dao<Choose> dao = new DaoImpl<>();
        Choose choose = dao.queryById(Choose.class, 9);
        System.out.println(choose.getUserChoosesById().size());
    }

    // 每个选项的用户
    @Test
    public void chooseUsersTest(){
        Dao<Choose> dao = new DaoImpl<>();
        Choose choose = dao.queryById(Choose.class, 1);
        choose.getUserChoosesById().forEach(userChoose -> {
            System.out.println(userChoose.getUsersByUid().getName());
        });
    }

    @Test
    public void removeChooseByUid(){
        VoteService voteService = new VoteService();
        voteService.removeAllChoose(new Users(1));
    }

    @Test
    public void getUTC(){
        VoteService service = new VoteService();
        List<UserTopicChoose> list = service.queryChooseByUid(2);
        list.forEach(System.out::println);
    }
}
