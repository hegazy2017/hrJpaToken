package com.hr.demo.security.User.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.demo.security.User.entity.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
	Optional<AppUser>findByUserName(String userName);

}
