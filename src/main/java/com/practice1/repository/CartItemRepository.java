package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice1.entities.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
