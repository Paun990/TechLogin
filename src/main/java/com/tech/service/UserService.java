package com.tech.service;

import java.util.List;

import com.tech.entity.User;

public interface UserService {
	
	
	public static final String SPRING_QUALIFIER = "userService";
	public List<User> getUsers();
	public User getUserById(int id);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	
	public List<User> filterUser(String sort, String filter);

}
