package com.practice1.service;

import com.practice1.dto.AdminDto;
import com.practice1.entities.Admin;

public interface AdminService {
	Admin createAdmin(AdminDto admin);

	boolean checkEmail(String email);
	AdminDto save(AdminDto adminDto);

}
