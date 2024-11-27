package com.practice1.service.iplm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice1.entities.DonHang;
import com.practice1.repository.DonHangRepository;
import com.practice1.service.DonHangService;
@Service
public class DonHangServiceIplm implements DonHangService {
	
	@Autowired
	DonHangRepository dhRepo;

	@Override
	public DonHang update(Integer id, DonHang dh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DonHang> findDonHangById(int id) {
		
		return dhRepo.findById(id);
	}

	@Override
	public void deleteDh(DonHang dh) {
		dhRepo.delete(dh);
		
	}

	@Override
	public DonHang createDonHang(DonHang dh) {
		return dhRepo.save(dh);
	}

	@Override
	public List<DonHang> getAlldh() {
		
		return (List<DonHang>) dhRepo.findAll();
	}

	@Override
	public void saveDh(DonHang dh) {
		dhRepo.save(dh);
		
	}

	@Override
	public void deleteByid(int id) {
		dhRepo.deleteById(id);
		
	}

	@Override
	public Page<DonHang> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return dhRepo.findAll(pageable);
	}

}
