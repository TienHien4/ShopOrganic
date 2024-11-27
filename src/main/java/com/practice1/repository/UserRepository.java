package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice1.entities.Customer;


@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {

	public boolean existsByUsername(String username);

	Customer findByUsername(String username);

}
