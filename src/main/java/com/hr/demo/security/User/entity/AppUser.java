package com.hr.demo.security.User.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity()
@Table(name = "sec_users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private String fullName;
	
	private String userName;
	
	private String password ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sec_user_roles" ,
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OrderColumn(name = "id")
	private Set<Role> roles = new HashSet<>();
	
	private boolean isEnabled;
	
	private boolean isCredentialsNonExpired;
	
	private boolean isAccountNonLocked;
	
	private boolean isAccountNonExpired;
	
	
	public AppUser(Long id) {
		super();
		this.id = id;
	}

}

	
	

