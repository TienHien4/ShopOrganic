package com.practice1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.practice1.entities.CartItem;
import com.practice1.entities.SanPham;
import com.practice1.entities.Customer;
import com.practice1.entities.ShoppingCart;
import com.practice1.repository.RoleRepository;
import com.practice1.repository.SanPhamRepository;
import com.practice1.repository.UserRepository;
import com.practice1.service.CartItemService;
import com.practice1.service.SanPhamService;
import com.practice1.service.ShoppingCartService;
import com.practice1.service.UserService;

@Controller
public class CilentController1 {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	SanPhamService spService;
	
	@Autowired
	UserService userService;

	@Autowired
	SanPhamRepository spRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	CartItemService itemService;

	
// Get danh sach san pham(phan trang)
	@GetMapping({"/index", "/"})
	public String index(Model model, Principal principal) {
		return findPaginated(1, model, principal);
   }

	@GetMapping("/index/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Principal principal) {
	    int pageSize = 5;
	    Page < SanPham> page = spService.findPaginated(pageNo, pageSize);
	    List <SanPham> SanPhams = page.getContent();
//		int cartId;
//		String username = principal.getName();
//		Customer customer = userRepo.findByUsername(username);
//		cartId = Integer.valueOf(customer.getShoppingCart().getId());
//		int totalQuantity = itemService.getTotalQuantityInCart(cartId);
//		model.addAttribute("totalQuantity", totalQuantity);
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("SanPhams", SanPhams);
	    model.addAttribute("newProduct1", spService.sortByPriceAsc());
		model.addAttribute("newProduct2", spService.sortByPriceDesc());
		model.addAttribute("newProduct", spService.getNewSP());
	    return "user/index";
	}
	
	
	
//	@GetMapping("/index/find1")
//	public String findSanPhamById(Model model, @RequestParam(value = "keyWord") String keyWord) {
//		List<SanPham> Product = spService.findSpByText(keyWord);
//		 model.addAttribute("Product", Product);
//		return "index/resultFind";
//	}
	
	@GetMapping("/index/find1")
	public String findSanPhamById(Model model, @RequestParam(value = "keyWord") String keyWord) {
		List<SanPham> Product = spService.findSpByText(keyWord);
		 model.addAttribute("p", Product);
		 model.addAttribute("newProduct", spService.getNewSP());
		return "user/resultFind";
	}
	
	
	
	
	@GetMapping("/resultFind1/{type}")
	public String FindType(Model model, @PathVariable(value = "type") String type) {
		List<SanPham> Product = spService.findSpByText(type);
		 model.addAttribute("type", Product);
		 model.addAttribute("newProduct", spService.getNewSP());
		return "user/resultFind1";
	}
	


	@GetMapping(value = "/blog")
	public String Blog() {
		return "user/blog";
	}
	//Check san pham theo id
	@GetMapping("/shop-details/{id}")
	public String GetDetailProduct(Model model, @PathVariable(value = "id") int id) {
		SanPham sp = spService.findSpById(id);
		model.addAttribute("sp", sp);
		return "user/shop-details";
	}
	
	//Get danh sach san pham trong cart
	@GetMapping(value = "/shoping-cart")
	public String ShoppingCart(Model model, Principal principal) {
		 if(principal == null){
	            return "redirect:/signin";
	        }
	        String username = principal.getName();
	        Customer customer = userRepo.findByUsername(username);
	        ShoppingCart shoppingCart = customer.getShoppingCart();
		    model.addAttribute("shoppingCart", shoppingCart);
		return "user/shoping-cart";
	}
	
	
	
	// Add san pham vao cart
	@PostMapping(value = "/addItem")
	public String AddItem(@RequestParam("id") int productId, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,  Principal principal) {	
		if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
		SanPham sp = spService.findSpById(productId);
		Customer customer = userRepo.findByUsername(username);
		cartService.addItem(sp, quantity, customer);
		return "redirect:/index";
	}
	
	@PostMapping(value = "/addItem5")
	public String AddItem5(@RequestParam("id") int productId, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,  Principal principal) {	
		if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
		SanPham sp = spService.findSpById(productId);
		Customer customer = userRepo.findByUsername(username);
		cartService.addItem(sp, quantity, customer);
		return "redirect:/shop-grid";
	}
	
	@PostMapping(value = "/addItem1")
	public String AddItem1(@RequestParam("id") int productId, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,  Principal principal) {	
		if(principal == null){
            return "redirect:/signin";
        }
        String username = principal.getName();
		SanPham sp = spService.findSpById(productId);
		Customer customer = userRepo.findByUsername(username);
		cartService.addItem(sp, quantity, customer);
		return "redirect:/shoping-cart";
	}
	@PostMapping(value="/reduceItem")
	public String ReduceItem(@RequestParam("id") int productId,  Principal principal) {
		if(principal == null){
            return "redirect:/signin";
        }
		String username = principal.getName();
		SanPham sp = spService.findSpById(productId);
		Customer customer = userRepo.findByUsername(username);
		cartService.reduceItem(sp, customer);
		return "redirect:/shoping-cart";
	}
	@PostMapping(value = "/deleteItem")
	public String deleteItem(@RequestParam("id") int productId,  Principal principal) {
		if(principal == null){
            return "redirect:/signin";
        }
		SanPham sp = spService.findSpById(productId);
		String username = principal.getName();
		Customer user = userRepo.findByUsername(username);
		cartService.deleteItem(sp, user);
		return "redirect:/shoping-cart";
	}

	@GetMapping(value = "/blog-details")
	public String BlogDetails() {
		return "user/blog-details";
	}

	@GetMapping(value = "/checkout")
	public String Checkout(Principal principal, Model model) {
		if(principal == null){
            return "redirect:/signin";
        }
		String username = principal.getName();
		Customer user = userRepo.findByUsername(username);
		List<CartItem> items = cartService.getAllItem();
		model.addAttribute("items", items);
		 ShoppingCart shoppingCart = user.getShoppingCart();
		    model.addAttribute("shoppingCart1", shoppingCart);
		
		return "user/checkout";
	}
	
	
	
	

	@GetMapping(value = "/contact")
	public String contact() {
		return "user/contact";
	}
	
	
	@GetMapping("/shop-grid")
	public String Shop_Grid(Model model) {
		return findPaginated1(model, 1);
   }
	
	

	@GetMapping(value = "/shop-grid/{PageNumber}")
	public String findPaginated1(Model model, @PathVariable(value = "PageNumber") int PageNumber) {
		 int pageSize = 9;
		    Page < SanPham> page = spService.findPaginated(PageNumber, pageSize);
		    List <SanPham> sanPham = page.getContent();
		    model.addAttribute("currentPage1", PageNumber);
		    model.addAttribute("totalPages1", page.getTotalPages());
		    model.addAttribute("totalItems1", page.getTotalElements());
		    model.addAttribute("SpShop", sanPham);
		    model.addAttribute("getSpShop", spRepo.findAll());
		    model.addAttribute("newProduct", spService.getNewSP());

		return "user/shop-grid";
	}

	
	@GetMapping(value = "/sortByPriceAsc/{PageNumber}")
	public String SortByPriceAsc(Model model,
			@PathVariable(value = "PageNumber") int PageNumber,
            @RequestParam(defaultValue = "price") String sortBy) {
		int pageSize = 9;
		 List <SanPham> sanPham = spRepo.findAll();
		Page<SanPham> page = spService.sortByPriceAsc(PageNumber, pageSize, sortBy);
		List <SanPham> listProducts1 = page.getContent();
		 model.addAttribute("currentPage2", PageNumber);
		model.addAttribute("totalPages2", page.getTotalPages());
		model.addAttribute("totalItems2", page.getTotalElements());
		model.addAttribute("listSP1", listProducts1);
		model.addAttribute("SpShop1", sanPham);
		model.addAttribute("newProduct", spService.getNewSP());
		return "user/shop-grid1";
	}
	
	@GetMapping(value = "/sortByPriceDesc/{PageNumber}")
	public String SortByPriceDesc(Model model, @PathVariable(value = "PageNumber") int PageNumber,
            @RequestParam(defaultValue = "price") String sortBy) {
		int pageSize = 9;
		List <SanPham> sanPham = spRepo.findAll();
		Page<SanPham> page = spService.sortByPriceDesc(PageNumber, pageSize, sortBy);
		List <SanPham> listProducts2 = page.getContent();
		model.addAttribute("currentPage3", PageNumber);
		model.addAttribute("totalPages3", page.getTotalPages());
		model.addAttribute("totalItems3", page.getTotalElements());
		model.addAttribute("SpShop2", sanPham);
		model.addAttribute("listSP2", listProducts2);
		model.addAttribute("newProduct", spService.getNewSP());
		return "user/shop-grid2";
	}
	
	

	@GetMapping("/resultFind2/{type}")
	public String FindType2(Model model, @PathVariable(value = "type") String type) {
		List<SanPham> Product = spService.findSpByText(type);
		 model.addAttribute("type2", Product);

		return "user/resultFind2";
	}
	

}
