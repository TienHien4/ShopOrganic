package com.practice1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.practice1.entities.Customer;
import com.practice1.entities.ShoppingCart;
import com.practice1.repository.UserRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

}
