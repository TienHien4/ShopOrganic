package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice1.entities.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
