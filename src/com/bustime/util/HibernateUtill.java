package com.bustime.util;

import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Rushan on 4/16/2015.
 */
public class HibernateUtill {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
//        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
//            serviceRegistry = new ServiceRegistryBuilder().applySetting(configuration.getProperties()).buildServiceRegistry();
//        }catch ()
    }

}























