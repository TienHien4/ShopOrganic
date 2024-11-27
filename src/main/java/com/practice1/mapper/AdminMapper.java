package com.practice1.mapper;
import org.springframework.stereotype.Component;
import com.practice1.dto.AdminDto;
import com.practice1.entities.Admin;


@Component
public class AdminMapper {
	public AdminDto toAdminDto(Admin admin) {
		AdminDto dto = new AdminDto();
		dto.setFirstName(admin.getFirstName());
		dto.setLastName(admin.getLastName());
		dto.setUsername(admin.getUsername());
		dto.setEmail(admin.getEmail());
		dto.setPassword(admin.getPassword());
		return dto;
	
	}
	
	public Admin toAdmin(AdminDto admindto) {
		Admin admin = new Admin();
		admin.setFirstName(admindto.getFirstName());
		admin.setLastName(admindto.getLastName());
		admin.setUsername(admindto.getUsername());
		admin.setPassword(admindto.getPassword());
		return admin;
	
	}


}
