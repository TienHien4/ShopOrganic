package com.practice1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.practice1.dto.UserDto;
import com.practice1.entities.Customer;
import com.practice1.service.UserService;
import com.practice1.service.iplm.JwtTokenProvider;

@Controller
public class HomeController {
	@Autowired
    AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtService = new JwtTokenProvider();
	

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/register")
	public String Register(Model model)
	{

		UserDto user = new UserDto();
		model.addAttribute("userDto", user);
		return "home/register";
	}
	
	@PostMapping("/createUser")
	public String createrUser(UserDto userDto) {
		userService.createUser(userDto);
		return "home/login";
	}
	
	@GetMapping("/signin")
	public String Login()
	{
		return "home/login";
	}
	
	  @PostMapping("/login")
	    public ModelAndView login(HttpServletRequest request, Customer user) {
	        ModelAndView modelAndView = new ModelAndView();
	        try {
	            if (userService.checkLogin(user)) {
	                String token = jwtService.generateToken(user.getUsername());
	                // Đăng nhập thành công, chuyển hướng đến trang "user/index"
	                modelAndView.setViewName("redirect:/user/index");
	                // Lưu token vào session hoặc cookie nếu cần thiết
	                // request.getSession().setAttribute("token", token);
	            } else {
	                // Đăng nhập thất bại, trả về trang đăng nhập với thông báo lỗi
	                modelAndView.addObject("error", "Wrong userId and password");
	                modelAndView.setViewName("home/login");
	            }
	        } catch (Exception ex) {
	            // Xử lý lỗi nếu có
	            modelAndView.addObject("error", "Server Error");
	            modelAndView.setViewName("home/login");
	        }
	        return modelAndView;
	    }
	
	

}

