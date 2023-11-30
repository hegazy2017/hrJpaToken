package com.hr.demo.security.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.demo.security.User.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	
	Role findByName(String name);
}
