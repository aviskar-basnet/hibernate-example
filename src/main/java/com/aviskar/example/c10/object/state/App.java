package com.aviskar.example.c10.object.state;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

	public static void main(String[] args) throws InterruptedException {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.configure().build();
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addAnnotatedClass(Boy.class);
		Metadata metadata = metadataSources.getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

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
		serviceRegistry.close();
	}
}
