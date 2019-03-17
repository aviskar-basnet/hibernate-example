package com.aviskar.example.c09.criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Boy.class);
		configuration.addAnnotatedClass(Girl.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Boy sabin = new Boy("Sabin");
		sabin.setGirl(new Girl("Sabina"));
		Boy bijay = new Boy("Bijay");
		bijay.setGirl(new Girl("Niti"));

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(sabin);
		session.persist(bijay);
		session.getTransaction().commit();
		session.close();

		// Criteria API
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Boy.class);
		criteria.add(Restrictions.eq("name", "Bijay"));
		Boy boy1 = (Boy) criteria.uniqueResult();
		System.out.println(boy1.getName());
		session.close();

		// Query Language
		session = sessionFactory.openSession();
		Query<Boy> query = session.createQuery("select b from Boy b where b.name = :name", Boy.class);
		query.setString("name", "Bijay");
		Boy boy2 = (Boy) query.getSingleResult();
		System.out.println(boy2.getName());
		session.close();

		// Native SQL
		session = sessionFactory.openSession();
		NativeQuery nativeQuery = session.createSQLQuery("select * from boy where name = :name");
		nativeQuery.setString("name", "Bijay");
		List<Object[]> objects = (List<Object[]>) nativeQuery.list();
		System.out.println(objects.get(0)[3]);
		session.close();

		sessionFactory.close();
	}
}
