package com.practice1.mapper;
import org.springframework.stereotype.Component;
import com.practice1.dto.AdminDto;
import com.practice1.dto.UserDto;
import com.practice1.dto.UserResponse;
import com.practice1.entities.Admin;
import com.practice1.entities.Customer;


@Component
public class UserMapper {
	public UserResponse toUserResponse(Customer user) {
		 UserResponse dto = new  UserResponse();
		dto.setFullName(user.getFullName());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		return dto;
	
	}

	public Customer toUser(UserDto userdto) {
		Customer user = new Customer();
		user.setFullName(userdto.getFullName());
		user.setUsername(userdto.getUsername());
		user.setPassword(userdto.getPassword());
		user.setEmail(userdto.getEmail());
		return user;
	
	}


}
