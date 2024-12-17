package com.practice1.service.iplm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.practice1.dto.UserDto;
import com.practice1.dto.UserResponse;
import com.practice1.entities.Admin;
import com.practice1.entities.Customer;
import com.practice1.entities.Role;
import com.practice1.mapper.UserMapper;
import com.practice1.repository.RoleRepository;
import com.practice1.repository.UserRepository;
import com.practice1.service.UserService;
@Service
public class UserServiceIplm implements UserService{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	@Autowired
	private UserMapper mapper;
	public static List<Customer> listUser = new ArrayList<>();




	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByEmail(email);
	}



	@Override
	public UserResponse createUser(UserDto dto){
		
		Customer user = mapper.toUser(dto);
		user.setVaitro(Arrays.asList(roleRepo.findByroleName("ROLE_USER")));      
	    user.setPassword(passwordEncode.encode(dto.getPassword()));
		return mapper.toUserResponse(userRepo.save(user));
	}



	@Override
	public UserDto save(UserDto Dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean checkLogin(UserDto user) {
		// Kiểm tra xem username có tồn tại không
		Customer dbUser = userRepo.findByUsername(user.getUsername());
		if (dbUser == null) {
			return false;
		}

		// So sánh mật khẩu đã mã hóa
		return passwordEncode.matches(user.getPassword(), dbUser.getPassword());
	}

	
	
	public Customer loadUserByUsername(String username) {
		for (Customer user : listUser) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}







}
