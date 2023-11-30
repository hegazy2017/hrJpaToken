package com.hr.demo.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hr.demo.security.User.entity.AppUser;
import com.hr.demo.security.User.entity.Role;
import com.hr.demo.security.User.service.RoleService;
import com.hr.demo.security.User.service.UserService;
@Component
public class StartUp implements CommandLineRunner{

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if (roleService.findAll().isEmpty()) {			
			roleService.insertRole(new Role(null, "admin"));
			roleService.insertRole(new Role(null, "user"));
			roleService.insertRole(new Role(null, "employee"));
		}
		
		
		if (userService.findAll().isEmpty()) {
			
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(roleService.findByName("admin"));
			adminRoles.add(roleService.findByName("user"));
			
			Set<Role> userRoles = new HashSet<>();
			userRoles.add(roleService.findByName("user"));
			
			Set<Role>  empRoles = new HashSet<>();
			empRoles.add(roleService.findByName("employee"));
			
			userService.insertUser(new AppUser(null, "Ahmed Hegazy", "ahmed", "123", adminRoles,true,true,true,true));
			
			userService.insertUser(new AppUser(null, "Ali Mohamed", "ali", "123", userRoles,true,true,true,true));
			
			userService.insertUser(new AppUser(null, "Ahmed Ebraheem", "ibrahim", "123", empRoles,true,true,true,true));
		}
	}

}
