package com.tech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.dao.UserDao;
import com.tech.entity.User;
import com.tech.service.UserService;


@Service(value=UserService.SPRING_QUALIFIER)
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}


	public List<User> filterUser(String sort, String filter) {
		
		return userDao.filterUser(sort, filter);
	}


}
