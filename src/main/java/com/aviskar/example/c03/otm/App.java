package com.aviskar.example.c03.otm;

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

		Session session = sessionFactory.openSession();

		Boy bijay = new Boy();
		bijay.setName("Sabin");

		Girl sabina = new Girl();
		sabina.setName("Sabina");
		sabina.setBoy(bijay);

		Girl rita = new Girl();
		rita.setName("Rita");
		rita.setBoy(bijay);

		session.save(sabina);
		session.save(rita);

		session.close();

		sessionFactory.close();
		serviceRegistry.close();
	}
}
