package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice1.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByroleName(String role);
}