package com.aviskar.example.c08.mtm.bidirectional;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Boy.class);
		configuration.addAnnotatedClass(Girl.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Set<Girl> girls = new HashSet<>();
		Girl niti = new Girl("Niti");
		girls.add(niti);
		Girl asmi = new Girl("Asmi");
		girls.add(asmi);

		Set<Boy> boys = new HashSet<>();
		Boy aashish = new Boy("Aashish");
		boys.add(aashish);
		Boy sabin = new Boy("Sabin");
		boys.add(sabin);

		niti.setBoys(boys);
		asmi.setBoys(boys);
		aashish.setGirls(girls);
		sabin.setGirls(girls);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(aashish);
		session.persist(sabin);
		session.getTransaction().commit();
		session.close();

		sessionFactory.close();
	}
}
