package com.tech.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tech.dao.UserDao;
import com.tech.entity.User;


@Repository(value=UserDao.SPRING_QUALIFIER)
@Transactional
public class UserDaoImpl implements UserDao {

	SessionFactory sessionFactory;
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public List<User> getUsers() {	
		Criteria res = sessionFactory.getCurrentSession().createCriteria(User.class);
		return res.list();
	}


	public User getUserById(int id) {
		User res = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
		return res;
	}


	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}


	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}


	public void deleteUser(int id) {
		User res = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
		sessionFactory.getCurrentSession().delete(res);
	}


	public List<User> filterUser(String sort, String filter) {
		Criteria res = sessionFactory.getCurrentSession().createCriteria(User.class);
		
		if(sort.equals("asc")){
			res.addOrder(Order.asc("name"));
		}else if(sort.equals("desc")){
			res.addOrder(Order.desc("name"));
		}
		
		res.add(Restrictions.like("name", "%" + filter + "%"));
		
		return res.list();
			
	}

	

}
