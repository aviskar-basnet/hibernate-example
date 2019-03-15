package com.aviskar.example.c04.mto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

	public static void main(String[] args) {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry serviceRegistry = serviceRegistryBuilder
				.configure().build();
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addAnnotatedClass(Boy.class);
		metadataSources.addAnnotatedClass(Girl.class);
		Metadata metadata = metadataSources.getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();

		Boy bijay = new Boy("Bijay");
		Girl sabina = new Girl("Sabina");
		sabina.setBoy(bijay);
		Girl rita = new Girl("Rita");
		rita.setBoy(bijay);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(sabina);
		session.persist(rita);
		session.getTransaction().commit();
		session.close();

		sessionFactory.close();
		serviceRegistry.close();
	}
}
