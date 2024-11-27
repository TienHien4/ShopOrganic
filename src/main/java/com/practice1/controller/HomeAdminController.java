package com.practice1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.practice1.dto.SanPhamDto;
import com.practice1.entities.Admin;
import com.practice1.entities.SanPham;
import com.practice1.entities.Customer;
import com.practice1.repository.AdminRepository;
import com.practice1.repository.SanPhamRepository;
import com.practice1.service.AdminService;
import com.practice1.service.SanPhamService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeAdminController {
	@Autowired
	SanPhamService spService;

	@Autowired
	SanPhamRepository spRepo;

	@Autowired
	AdminRepository adminRepo;


	@GetMapping("/admin/home")
	public String index(Model model, Principal p, HttpSession session) {
		model.addAttribute("SanPhams", spRepo.findAll());
		return findPaginated(1, model);
	}

	@GetMapping("/admin/home/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page<SanPham> page = spService.findPaginated(pageNo, pageSize);
		List<SanPham> SanPhams = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("SanPhams", SanPhams);
		return "admin/QLsanpham";
	}


	//	@GetMapping("/admin/find/{pageNo}")
//	public String SearchProduct(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(value = "keyword") String keyword, Principal principal) {
//
//		if(principal == null) {
//			return "redirect:/admin/home";
//		}
//		Page<SanPham> SanPhams = spService.SearchSP(keyword, pageNo);
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", SanPhams.getTotalPages());
//		model.addAttribute("totalItems", SanPhams.getSize());
//		model.addAttribute("SanPhams", SanPhams);
//		return "admin/resultProduct";
//	}
//	@GetMapping("/admin/find1")
//	public String findSanPhamById(Model model, @RequestParam("id") int id) {
//		Optional <SanPham> Product = spService.findSanPhamById(id);
//		 model.addAttribute("Product", Product);
//		return "admin/resultProduct1";
//	}
	@GetMapping("/admin/home/find1")
	public String findSanPhamById(Model model, @RequestParam(value = "id") int id) {
		SanPham Product = spService.findSpById(id);
		model.addAttribute("Product", Product);
		return "admin/resultProduct1";
	}



	@GetMapping("/admin/home/createSp")
	public String addSp(Model model) {
		SanPhamDto sp = new SanPhamDto();
		model.addAttribute("spDTO", sp);
		return "admin/createSp";
	}

	@PostMapping("/createSp")
	public String createSp(Model model, @ModelAttribute("spDTO") SanPhamDto spDTO) {
		SanPham sp = null;
		Path path = Paths.get("uploads/");
		String image = "logo.png";
		try {

			InputStream inputStream = spDTO.getPhoto().getInputStream();
			Files.copy(inputStream, path.resolve(spDTO.getPhoto().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			image = spDTO.getPhoto().getOriginalFilename().toString();

		} catch (Exception e) {
			e.printStackTrace();

		}
		sp = new SanPham(spDTO.getId(), image, spDTO.getNameProduct(), spDTO.getProductInformation(), spDTO.getWeight(), spDTO.getType(), spDTO.getPrice(), spDTO.getPercentSale());
		spService.saveSp(sp);
		return "redirect:/admin/home";
	}


	@GetMapping("/admin/home/edit/{id}")
	public String updateSp(@PathVariable("id") int id, Model model) {
		Optional<SanPham> sps = spService.findSanPhamById(id);
		model.addAttribute("sps", sps);
		SanPhamDto dto = null;
		if (sps.isPresent()) {
			SanPham sp = sps.get();
			File file = new File("uploads/" + sp.getPhoto());
			FileInputStream input;


			try {
				input = new FileInputStream(file);
				MultipartFile multiPhoto =
						new MockMultipartFile("file", file.getName(), "text/plain", org.apache.commons.io.IOUtils.toByteArray(input));
				dto = new SanPhamDto(sp.getId(), multiPhoto, sp.getNameProduct(), sp.getProductInformation(), sp.getWeight(), sp.getType(), sp.getPrice(), sp.getPercentSale());
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}else {
			dto = new SanPhamDto();
		}

		model.addAttribute("spDTO", dto);
		return "admin/updateSp";
	}
	@PostMapping("/updateSp/{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute("spDTO") SanPhamDto spDTO) {
		SanPham sp = spService.findSanPhamById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
		Path path = Paths.get("uploads/");
		String image = sp.getPhoto(); // Lấy ảnh cũ mặc định

		try {
			if (!spDTO.getPhoto().isEmpty()) { // Nếu có ảnh mới
				InputStream inputStream = spDTO.getPhoto().getInputStream();
				Files.copy(inputStream, path.resolve(spDTO.getPhoto().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				image = spDTO.getPhoto().getOriginalFilename();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sp.setPhoto(image); // Cập nhật ảnh
		sp.setNameProduct(spDTO.getNameProduct());
		sp.setProductInformation(spDTO.getProductInformation());
		sp.setWeight(spDTO.getWeight());
		sp.setType(spDTO.getType());
		sp.setPrice(spDTO.getPrice());
		sp.setPercentSale(spDTO.getPercentSale());

		spService.saveSp(sp);
		return "redirect:/admin/home";
	}




	@GetMapping("/delete/{id}")
    public String deteteSpByid(@PathVariable("id") int id)
    {
    	this.spService.deleteByid(id);
    	return "redirect:/admin/home";
    }

}
