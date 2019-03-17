package com.aviskar.example.c05.otm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Boy.class);
		configuration.addAnnotatedClass(Girl.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		List<Girl> girls = new ArrayList<>();
		Girl sabina = new Girl("Sabina");
		girls.add(sabina);
		Girl rita = new Girl("Rita");
		girls.add(rita);

		Boy bijay = new Boy("Bijay");
		bijay.setGirls(girls);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(bijay);
		session.getTransaction().commit();
		session.close();

		sessionFactory.close();
	}
}
