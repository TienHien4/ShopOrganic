package com.practice1.service.iplm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice1.dto.AdminDto;
import com.practice1.entities.Admin;
import com.practice1.mapper.AdminMapper;
import com.practice1.repository.AdminRepository;
import com.practice1.repository.RoleRepository;
import com.practice1.service.AdminService;
@Service
public class AdminServiceIplm implements AdminService{
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private AdminMapper adminMapper;

	

	@Override
	public boolean checkEmail(String email) {
		return adminRepo.existsByEmail(email);
	}
	
	 @Override
	    public AdminDto save(AdminDto adminDto) {
	        Admin admin = new Admin();
	        admin.setFirstName(adminDto.getFirstName());
	        admin.setLastName(adminDto.getLastName());
	        admin.setUsername(adminDto.getUsername());
	        admin.setPassword(adminDto.getPassword());
	        admin.setEmail(adminDto.getEmail());
	        admin.setRoles(Arrays.asList(roleRepo.findByroleName("ADMIN")));
	        return adminMapper.toAdminDto(adminRepo.save(admin));
	    }

	@Override
	public Admin createAdmin(AdminDto adminDto) {
		Admin admin = new Admin();
		  admin.setFirstName(adminDto.getFirstName());
	        admin.setLastName(adminDto.getLastName());
	        admin.setUsername(adminDto.getUsername());
	        admin.setPassword(adminDto.getPassword());
	        admin.setRoles(Arrays.asList(roleRepo.findByroleName("ADMIN")));
	        
	        admin.setPassword(passwordEncode.encode(adminDto.getPassword()));
		return adminRepo.save(admin);
	}

	

}
