package com.tech.dao;

import java.util.List;

import com.tech.entity.User;


public interface UserDao {
	


	public static final String SPRING_QUALIFIER = "userDao";
	public List<User> getUsers();
	public User getUserById(int id);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public List<User> filterUser(String sort, String filter);
	
}
