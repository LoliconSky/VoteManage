package com.bfchengnuo.common.bean;

import com.bfchengnuo.po.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给前台用的数据
 * 比起 Topic 加了个字段表示用户选的是那个
 * Created by 冰封承諾Andy on 2018/5/26.
 */
public class TopicPlus {
    // 题目
    private Topic topic;
    // 选项与选择
    private List<ChooseAndSelect> chooseAndSelect = new ArrayList<>();

    public TopicPlus() {
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<ChooseAndSelect> getChooseAndSelect() {
        return chooseAndSelect;
    }

    public void setChooseAndSelect(List<ChooseAndSelect> chooseAndSelect) {
        this.chooseAndSelect = chooseAndSelect;
    }
}
