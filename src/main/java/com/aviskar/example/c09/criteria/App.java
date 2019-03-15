package com.aviskar.example.c09.criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

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

		Boy sabin = new Boy("Sabin");
		sabin.setGirl(new Girl("Sabina"));
		Boy bijay = new Boy("Bijay");
		sabin.setGirl(new Girl("Niti"));

		Session session = sessionFactory.openSession();
		session.save(sabin);
		session.save(bijay);
		session.close();

		session = sessionFactory.openSession();
		Boy boy = session.find(Boy.class, 1L);
		System.out.println(boy.getName());
		session.close();

		session = sessionFactory.openSession();
		Boy boy2 = (Boy) session.createCriteria(Boy.class)
				.add(Restrictions.eq("name", "Bijay")).uniqueResult();
		System.out.println(boy2.getName());
		session.close();

		sessionFactory.close();
		serviceRegistry.close();
	}
}
