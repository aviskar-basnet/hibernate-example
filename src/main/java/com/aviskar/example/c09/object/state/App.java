package com.aviskar.example.c09.object.state;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Boy.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Boy sabin = new Boy("Sabin");
		Boy bijay = new Boy("Bijay");

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(sabin);
		session.persist(bijay);
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();
		Boy boy = session.find(Boy.class, 1L);
		session.remove(boy);
		session.getTransaction().commit();
		session.close();

		sessionFactory.close();
	}
}
