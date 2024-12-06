package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice1.entities.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    @Query("SELECT SUM(c.quantity) FROM CartItem c WHERE c.cart.id = :cartId")
    Integer findTotalQuantityByCartId(@Param("cartId") int cartId);



}
