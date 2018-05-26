package com.bfchengnuo.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Kerronex
 * @version 创建时间：2018年5月23日 下午3:49:20
 */
public class SF {
    private static SessionFactory f;

    private SF() {
    }

    static {
        // configure 会使用默认 hibernate.cfg.xml 文件作为配置
        Configuration config = new Configuration().configure();
        f = config.buildSessionFactory();
    }

    public static Session getSession() {
        return f.openSession();
    }
}
