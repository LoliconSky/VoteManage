package com.bfchengnuo.dao;

import com.bfchengnuo.common.bean.ChooseAndCount;
import com.bfchengnuo.factory.SF;
import com.bfchengnuo.po.Choose;
import org.hibernate.*;
import org.hibernate.transform.Transformers;

import java.io.Serializable;
import java.util.List;


/**
 * @author Kerronex
 * @version 创建时间：2018年5月25日 上午9:48:53
 */
public class DaoImpl<T> implements Dao<T> {
    private Session session = null;
    private Transaction tx = null;

    @Override
    public void add(T t) {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public T queryById(Class<T> cls, Serializable id) {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            @SuppressWarnings("unchecked")
            T t = (T) session.get(cls, id);
            tx.commit();
            return t;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<T> queryAll(Class<T> cls) {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + cls.getName());
            List<T> list = query.list();
            tx.commit();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public int update(T t) {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            session.update(t);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 1;
    }

    @Override
    public int getCount4Choose(Choose choose) {
        return 0;
    }

    @Override
    public List<ChooseAndCount> getChooseCount() {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            String sql = "SELECT c.id id,conte conte,count(conte) cSum FROM choose c LEFT JOIN user_choose uc ON c.id = uc.cid GROUP BY conte,c.id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Transformers.aliasToBean(ChooseAndCount.class));
            List<ChooseAndCount> list = query.list();
            tx.commit();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public T queryByName(Class<T> cls, String name) {
        try {
            session = SF.getSession();
            tx = session.beginTransaction();
            String hql = "from Users where name=?";
            Query query = session.createQuery(hql);
            query.setString(0, name);
            T t = (T) query.uniqueResult();
            tx.commit();
            return t;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

}
