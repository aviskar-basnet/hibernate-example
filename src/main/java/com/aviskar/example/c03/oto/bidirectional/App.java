package com.aviskar.example.c03.oto.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Boy.class);
		configuration.addAnnotatedClass(Girl.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Girl sabina = new Girl("Sabina");
		Boy sabin = new Boy("Sabin");
		sabin.setGirl(sabina);

		Session session = sessionFactory.openSession();
		session.save(sabin);
		session.close();

		sessionFactory.close();
	}
}
