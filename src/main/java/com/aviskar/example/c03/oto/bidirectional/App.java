package com.aviskar.example.c03.oto.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.aviskar.example.c02.oto.Boy;
import com.aviskar.example.c02.oto.Girl;

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

		Girl sabina = new Girl("Sabina");
		Boy sabin = new Boy("Sabin");
		sabin.setGirl(sabina);

		Session session = sessionFactory.openSession();
		session.save(sabin);
		session.close();

		sessionFactory.close();
		serviceRegistry.close();
	}
}
