package com.adda.app.Controller;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adda.app.Service.IUserService;
import com.adda.app.entity.Role;
import com.adda.app.entity.User;
import com.adda.app.entity.UserRole;
import com.adda.app.helper.UserFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
	private IUserService uservice;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception 
	{
		
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		
		roles.add(userRole);
		return this.uservice.CreateUser(user, roles);
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) 
	{
		return this.uservice.getUser(username);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) 
	{
		this.uservice.deleteuser(userId);
	}
	@ExceptionHandler
	public ResponseEntity<?> execeptionHandler(UserFoundException ex){
		return ResponseEntity .ok(ex);
				
	}
}
