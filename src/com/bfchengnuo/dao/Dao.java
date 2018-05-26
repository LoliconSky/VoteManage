package com.bfchengnuo.dao;

import com.bfchengnuo.common.bean.ChooseAndCount;
import com.bfchengnuo.po.Choose;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kerronex
 * @version 创建时间：2018年5月25日 上午9:46:50
 */
public interface Dao<T> {
    void add(T t);

    T queryById(Class<T> cls, Serializable id);

    List<T> queryAll(Class<T> cls);

    int update(T t);

    @Deprecated
    int getCount4Choose(Choose choose);

    List<ChooseAndCount> getChooseCount();

    T queryByName(Class<T> usersClass, String name);
}
