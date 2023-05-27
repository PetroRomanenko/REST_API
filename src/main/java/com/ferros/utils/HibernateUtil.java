package com.ferros.utils;

import com.ferros.model.Event;
import com.ferros.model.User;
import com.ferros.model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            Configuration configuration = buildConfiguration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration buildConfiguration() {
        Configuration configuration =new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Event.class);
        configuration.addAnnotatedClass(File.class);
        return configuration;
    }

    public static Session getSession(){
        return getSessionFactory().openSession();
    }


}
