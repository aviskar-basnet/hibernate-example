package com.aviskar.example.c06.otm.bidirectional;

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

		Boy bijay = new Boy("Bijay");

		List<Girl> girls = new ArrayList<>();
		Girl sabina = new Girl("Sabina");
		sabina.setBoy(bijay);
		girls.add(sabina);
		Girl rita = new Girl("Rita");
		rita.setBoy(bijay);
		girls.add(rita);

		bijay.setGirls(girls);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(sabina);
		session.persist(rita);
		session.getTransaction().commit();
		session.close();

		sessionFactory.close();
	}
}
