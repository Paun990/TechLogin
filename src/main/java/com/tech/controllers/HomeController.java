package com.tech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tech.entity.User;
import com.tech.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

	@Autowired
	UserService userService;

	/*
	 * @RequestMapping(value="/users", method=RequestMethod.GET,
	 * produces="application/json", consumes="application/json") public
	 * ResponseEntity<String> helloWorld() { return new
	 * ResponseEntity<String>("Hello World", HttpStatus.OK); }
	 */

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<User>> getUsers() {
		List<User> list = userService.getUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		userService.addUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/users/add/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/users/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user){
		User currentUser = userService.getUserById(id);
		currentUser.setName(user.getName());
		currentUser.setPass(user.getPass());
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/filter", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<User>> filterUser(@RequestParam String sort, @RequestParam String filter){
		List<User> list = userService.filterUser(sort, filter);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
}
