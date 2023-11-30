package com.hr.demo.security.User.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hr.demo.security.User.entity.Role;
import com.hr.demo.security.User.repository.RoleRepo;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	@Autowired
	private  RoleRepo roleRepo ;

	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
	public Role findById(Long id){
		return roleRepo.findById(id).orElse(null);
	}
	
	public Role findByName(String name){
		return roleRepo.findByName(name);
	}
	
	public Role insertRole(Role newRole){
		return roleRepo.save(newRole);
	}
}
