package com.hr.demo.security.User.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hr.demo.security.User.entity.AppUser;
import com.hr.demo.security.User.model.AppUserDetail;
import com.hr.demo.security.User.repository.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo ;
	
	@Autowired
	PasswordEncoder PasswordEncoder;

	public List<AppUser> findAll(){
		return userRepo.findAll();
	}
	
	public AppUser findById(Long id){
		return userRepo.findById(id).orElse(null);
	}
	
	public AppUser insertUser(AppUser newUser){
		newUser.setPassword(PasswordEncoder.encode(newUser.getPassword()));
		return userRepo.save(newUser);
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<AppUser> appUser=userRepo.findByUserName(username); 
	if(!appUser.isPresent()) {
		throw new UsernameNotFoundException("This User NOt Found");
	}
		return new AppUserDetail(appUser.get());
	}
	

	
}
