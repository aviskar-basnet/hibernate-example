package com.aviskar.example.c01.overview;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Student.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Hibernate create tables according to the entity classes.

		sessionFactory.close();
	}
}
