package com.natto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.natto.model.User;
import com.natto.util.UserUtil;

public class UserDao {

	public void saveUser(User user) {
		SessionFactory sessionFactory = UserUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();

	}

	public User getUserPojoByEmailAddress(String emailaddress1) {
		SessionFactory sessionFactory = UserUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userEmailAddress", emailaddress1));

		User user = (User) criteria.uniqueResult();
		return user;

	}

}
