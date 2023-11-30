package com.hr.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hr.demo.security.Auth.AuthFilter;
import com.hr.demo.security.Auth.JwtUnAuthResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	String [] PUBLIC_END_POINTS = {"/api/v1/auth/login", "/api/v1/auth/refresh-token", "/api/v1/auth/logout","/swagger-ui/**", "/v3/**"};
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUnAuthResponse jwtUnAuthResponse;
	
	//--------------------This For Define Users and Password------------------
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	/* Predefined Usera and passwords Roles And Users (In Memory)-
	 * 
	 auth.inMemoryAuthentication().withUser("admin").password("{noop}123").authorities("ADMIN").and()
	.withUser("user").password("{noop}123").authorities("USER");*/
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
	}
	
	//--------------------Manager Authorize on Request-----------------
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable()
	            .exceptionHandling()
	            .and().httpBasic()
	            .authenticationEntryPoint(jwtUnAuthResponse)
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authorizeRequests()
	            .antMatchers(PUBLIC_END_POINTS).permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	    }

	 @Override
	    @Bean
	    protected AuthenticationManager authenticationManager() throws Exception {
	        // TODO Auto-generated method stub
	        return super.authenticationManager();
	    }

	    @Bean
	    public AuthFilter authFilter() {
	        return new AuthFilter();
	    }
	


}
