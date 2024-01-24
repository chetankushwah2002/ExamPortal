package com.adda.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.adda.app.ServiceImpl.UserServiceImpl;
import com.adda.app.entity.Role;
import com.adda.app.entity.User;
import com.adda.app.entity.UserRole;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner{

	 @Autowired
		private UserServiceImpl uservice;
	 @Autowired
		private BCryptPasswordEncoder pe;
	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		// TODO Auto-generated method stub
//		System.out.println("starting code");
//		User user=new User();
//		user.setFirstName("sheetal");
//		user.setLastName("patidar");
//		user.setEmail("sheetal@gmail.com");
//		user.setEnabled(true);
//		user.setPassword(this.pe.encode("123"));
//		user.setPhone("7067576774");
//		user.setProfile("java developer");
//		user.setUsername("sheetal123");
//		
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//		Set <UserRole>userroleSet = new HashSet<>();
//		UserRole userrole = new UserRole();
//		userrole.setRole(role1);
//		userrole.setUser(user);
//		userroleSet.add(userrole);
//	    User user1=this.uservice.CreateUser(user, userroleSet);
//	    System.out.println(user1);
	}

}
