package com.practice1.conf.Customer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice1.entities.Customer;
import com.practice1.repository.UserRepository;


@Service
@Primary
public class UserDetailsServiceIplm implements UserDetailsService {
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = userRepo.findByUsername(username);
			 if(user== null){
		            throw new UsernameNotFoundException("Could not find username");
		        }
			  return new User(
		                user.getUsername(),
		                user.getPassword(),
		                user.getVaitro().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
		               
		             
	
	}

}