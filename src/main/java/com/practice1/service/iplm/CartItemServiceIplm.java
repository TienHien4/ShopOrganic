package com.practice1.service.iplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice1.entities.CartItem;
import com.practice1.repository.CartItemRepository;

@Service
public class CartItemServiceIplm implements com.practice1.service.CartItemService{
	@Autowired
	CartItemRepository itemRepo;

	@Override
	public List<CartItem> getAllItem() {
		
		return (List<CartItem>) itemRepo.findAll();
	}

}
