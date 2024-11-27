package com.practice1.service.iplm;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import com.practice1.dto.SanPhamDto;
import com.practice1.utils.ImageUpload;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.practice1.entities.SanPham;
import com.practice1.repository.SanPhamRepository;
import com.practice1.service.SanPhamService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SanPhamServiceIplm implements SanPhamService{

	@Autowired
	private SanPhamRepository spRepo;

	@Autowired
	private ImageUpload imageUpload;
	
	 @Override  
	  public void saveSp(SanPham sp) {


		 spRepo.save(sp);
	  }  
	
	@Override
	public SanPham update(MultipartFile imageProduct, SanPhamDto sp) throws IOException {
		SanPham productUpdate = spRepo.getById((sp.getId()));
		if (imageProduct.getBytes().length > 0) {
			if (imageUpload.checkExisted(imageProduct)) {
				productUpdate.setPhoto(productUpdate.getPhoto());
			} else {
				imageUpload.uploadImage(imageProduct);
				productUpdate.setPhoto(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
			}
			productUpdate.setNameProduct(sp.getNameProduct());
			productUpdate.setProductInformation(sp.getProductInformation());
			productUpdate.setPrice(sp.getPrice());
			productUpdate.setType(sp.getType());
			productUpdate.setWeight(sp.getWeight());
			productUpdate.setPercentSale(sp.getPercentSale());



		}
		return spRepo.save(productUpdate);
	}

	@SneakyThrows
	@Override
	public SanPham createSp(MultipartFile imageSp, SanPhamDto sp){

		SanPham product = new SanPham();

			if (imageSp == null) {
				sp.setPhoto(null);
			} else {
				imageUpload.uploadImage(imageSp);

			}

		product.setNameProduct(sp.getNameProduct());
		product.setProductInformation(sp.getProductInformation());
		product.setPrice(sp.getPrice());
		product.setType(sp.getType());
		product.setWeight(sp.getWeight());
		product.setPercentSale(sp.getPercentSale());
		return spRepo.save(product);
	}


	@Override
	public void deleteSp(SanPham sp) {
		spRepo.delete(sp);
	}

	@Override
	public SanPham createSanPham(SanPham sp) {
		return spRepo.save(sp);
	}

	@Override
	public List<SanPham> getAllsp() {
		
		return (List<SanPham>) spRepo.findAll();
	}

	@Override
	public SanPhamDto getId(int id) {
		 SanPham sp = spRepo.getReferenceById(id);
		 SanPhamDto dto = new SanPhamDto();
		 dto.setPrice(sp.getPrice());
		dto.setNameProduct(sp.getNameProduct());
		dto.setProductInformation(sp.getProductInformation());
		dto.setType(sp.getType());
		dto.setWeight(sp.getWeight());
		dto.setPercentSale(sp.getPercentSale());
		return dto;
	}


	@Override
	public Optional<SanPham> findSanPhamById(int id) {
		return spRepo.findById(id);
	}

	@Override
	public void deleteByid(int id) {
		spRepo.deleteById(id);
		
	}


	@Override
	public Page<SanPham> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -  1, pageSize);
		return spRepo.findAll(pageable);
	}

	@Override
	public Optional<SanPham> findOneSanPhamById(int id) {
		return spRepo.findById(id);
	}

	@Override
	public List<SanPham> SaveAll(List<SanPham> sps) {
		
		return (List<SanPham>) spRepo.saveAll(sps);
	}



	@Override
	public List<SanPham> getAllSP() {
		
		return (List<SanPham>) spRepo.findAll();
	}
	
	
	@Override
	public List<SanPham> getNewSP() {
		
		return (List<SanPham>) spRepo.newProduct();
	}
	


	@Override
	public SanPham findSpById(int id) {
		SanPham product = spRepo.FindSPById(id);
		return product;
	}

	@Override
	public List<SanPham> findSpByText(String keyWord) {
		List<SanPham> listProduct = spRepo.searchSP(keyWord);
		return listProduct;
	}

	@Override
	public List<SanPham> sortByPriceAsc() {
		List<SanPham> listProducts = spRepo.sortByPriceASC();
		return listProducts;
	}

	@Override
	public List<SanPham> sortByPriceDesc() {
		List<SanPham> listProducts = spRepo.sortByPriceDESC();
		return listProducts;
	}

	@Override
	public Page<SanPham> sortByPriceAsc(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo -  1, pageSize, Sort.by(sortBy));
		return spRepo.findAll(pageable);
	}

	@Override
	public Page<SanPham> sortByPriceDesc(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo -  1, pageSize, Sort.by(sortBy));
		return spRepo.sortByPriceDesc(pageable);
	}

	@Override
	public Page<SanPham> findSpByText1(int pageNo, int pageSize, String text) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return spRepo.searchSP1(text, pageable);
	}

	

	




	
	

}
