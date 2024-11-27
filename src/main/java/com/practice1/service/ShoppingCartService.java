package com.practice1.service;

import java.util.List;

import com.practice1.entities.CartItem;
import com.practice1.entities.SanPham;
import com.practice1.entities.ShoppingCart;
import com.practice1.entities.Customer;

public interface ShoppingCartService {
	ShoppingCart addItem(SanPham sp, int quantity, Customer customer);
	ShoppingCart updateItem(SanPham sp, int quantity, Customer customer);
	ShoppingCart deleteItem(SanPham sp, Customer customer);
	List<CartItem> getAllItem();
	ShoppingCart reduceItem(SanPham sp, Customer customer);
	
	

}
