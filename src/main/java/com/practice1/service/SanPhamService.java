package com.practice1.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.practice1.dto.SanPhamDto;
import com.practice1.entities.SanPham;
import org.springframework.web.multipart.MultipartFile;

public interface SanPhamService {
	
	public SanPham update(MultipartFile imageSp, SanPhamDto sp) throws IOException;

	public SanPham createSp(MultipartFile imageSp, SanPhamDto sp);
	public Optional<SanPham> findSanPhamById(int id);
	public List<SanPham> SaveAll(List<SanPham> sps);
	Optional<SanPham> findOneSanPhamById(int id);
	void deleteSp(SanPham sp);
	public SanPham createSanPham(SanPham sp);
	public List<SanPham> getAllsp();
	SanPhamDto getId(int id);
	void saveSp(SanPham sp);
	void deleteByid(int id);
	 Page <SanPham> findPaginated(int pageNo, int pageSize);
	 List<SanPham> getAllSP();
	 SanPham findSpById(int id);
	 List<SanPham> findSpByText(String text);
	 List<SanPham> sortByPriceAsc();
	 Page<SanPham> sortByPriceAsc(int pageNo, int pageSize, String sortBy);
	 List<SanPham> sortByPriceDesc();
	 Page<SanPham> sortByPriceDesc(int pageNo, int pageSize, String sortBy);
	List<SanPham> getNewSP();
	Page<SanPham> findSpByText1(int pageNo, int pageSize, String text);



}
