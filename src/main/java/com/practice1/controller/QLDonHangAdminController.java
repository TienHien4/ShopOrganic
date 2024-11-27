package com.practice1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.practice1.entities.DonHang;
import com.practice1.repository.DonHangRepository;
import com.practice1.service.DonHangService;
@Controller
public class QLDonHangAdminController {

	@Autowired
	DonHangService dhService;

	@Autowired
	DonHangRepository dhRepo;



	@GetMapping("/admin/home/home1")
	public String index2(Model model) {
		return findPaginated2(1, model);
	}

	@GetMapping("/admin/home/home1/{pageNo}")
	public String findPaginated2(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page<DonHang> page = dhService.findPaginated(pageNo, pageSize);
		List<DonHang> DonHangs = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("DonHangs", DonHangs);
		return "admin/QLdonhang";
	}

	@GetMapping("/admin/home/home1/createDh")
	public String addDh(Model model) {
		model.addAttribute("dh", new DonHang());
		return "admin/createDh";
	}

	@PostMapping("/createDh")
	public String createDh(@ModelAttribute DonHang dh) {
		dhService.createDonHang(dh);
		return "redirect:/admin/createDh";
	}

	@GetMapping("/admin/home/home1/editDh/{id}")
	public String updateDh(@PathVariable("id") int id, Model model) {
		Optional<DonHang> dh = dhService.findDonHangById(id);
		model.addAttribute("dh", dh);
		return "admin/updateDh";
	}

	@PostMapping("/updateDh/{id}")
	public String update(@PathVariable("id") int id, DonHang dh, BindingResult result) {
		dhService.saveDh(dh);
		return "redirect:/admin/home1";
	}



	@GetMapping("/deleteDh/{id}")
	public String deteteDhByid(@PathVariable("id") int id) {
		this.dhService.deleteByid(id);
		return "redirect:/admin/home1";
	}

}
