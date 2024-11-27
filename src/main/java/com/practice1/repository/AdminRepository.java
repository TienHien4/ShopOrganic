package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice1.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	public boolean existsByEmail(String email);

	Admin findByUsername(String username);

}
