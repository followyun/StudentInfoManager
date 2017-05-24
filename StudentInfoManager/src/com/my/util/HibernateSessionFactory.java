package com.my.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * 
 * �ù�������Ҫ�����ڻ�ȡhibernate��ܵ�session����
 * @author yun
 *
 */
public class HibernateSessionFactory {
	//private Session session;
	private  static SessionFactory sessionFactory;
	
	//˽�л����췽��
	private HibernateSessionFactory() {
		
	}

	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null) {
			//��ȡhibernate.cfg.xml
			Configuration config = new Configuration().configure();
			//��������ע�����
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
					applySettings(config.getProperties()).buildServiceRegistry();
			//��ȡxx.hbm.xml�ļ�
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
}
