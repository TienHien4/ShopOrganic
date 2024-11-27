package com.practice1.service;

import com.practice1.dto.UserDto;
import com.practice1.dto.UserResponse;
import com.practice1.entities.Role;
import com.practice1.entities.Customer;

public interface UserService {

	public UserResponse createUser(UserDto user);
	UserDto save(UserDto Dto);
	public boolean checkEmail(String email);
	public boolean checkLogin(Customer user);
	public Customer loadUserByUsername(String username);

}
