package com.practice1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.practice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/register")
	public String Register(Model model)
	{

		UserDto user = new UserDto();
		model.addAttribute("userDto", user);
		return "home/register";
	}

	@PostMapping("/createUser")
	public String createUser(@ModelAttribute("userDto") UserDto userDto, BindingResult result) {
		// Kiểm tra username đã tồn tại
		if (userRepository.existsByUsername(userDto.getUsername())) {
			result.rejectValue("username", "error.userDto", "Username đã tồn tại");
		}

		// Kiểm tra email đã tồn tại
		if (userRepository.existsByEmail(userDto.getEmail())) {
			result.rejectValue("email", "error.userDto", "Email đã tồn tại");
		}

		// Nếu có lỗi, quay lại trang đăng ký
		if (result.hasErrors()) {
			return "home/register";
		}

		// Tạo user mới
		userService.createUser(userDto);
		return "home/login";
	}


	@GetMapping("/signin")
	public String Login(Model model)
	{

		return "home/login";
	}

//	@PostMapping("/login")
//	public ModelAndView login(HttpServletRequest request, Customer user) {
//		ModelAndView modelAndView = new ModelAndView();
//
//		try {
//			// Kiểm tra xem username có tồn tại hay không
//			if (!userRepository.existsByUsername(user.getUsername())) {
//				modelAndView.addObject("error", "Username does not exist.");
//				modelAndView.setViewName("home/login");
//				return modelAndView;
//			}
//
//			// Kiểm tra đăng nhập (username và password)
//			if (userService.checkLogin(user)) {
//				// Đăng nhập thành công, tạo JWT token
//				String token = jwtService.generateToken(user.getUsername());
//
//				// Lưu token vào session (nếu cần)
//				request.getSession().setAttribute("token", token);
//
//				// Chuyển hướng đến trang "user/index"
//				modelAndView.setViewName("redirect:/user/index");
//			} else {
//				// Mật khẩu sai
//				modelAndView.addObject("error", "Wrong password. Please try again.");
//				modelAndView.setViewName("home/login");
//			}
//		} catch (Exception ex) {
//			// Xử lý lỗi hệ thống nếu có
//			modelAndView.addObject("error", "Server error. Please contact support.");
//			modelAndView.setViewName("home/login");
//		}
//
//		return modelAndView;
//	}

	@PostMapping("/login")
	public String login(@ModelAttribute("userDto") UserDto userDto, Model model) {
		if (!userService.checkLogin(userDto)) {
			model.addAttribute("error", "Sai username hoặc mật khẩu");
			return "home/login";
		}

		// Đăng nhập thành công
		return "redirect:/user/index";
	}






}

