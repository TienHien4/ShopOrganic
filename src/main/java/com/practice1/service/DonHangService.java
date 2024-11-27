package com.practice1.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.practice1.entities.DonHang;

public interface DonHangService {

	public DonHang update(Integer id, DonHang dh);
	public Optional<DonHang> findDonHangById(int id);
	void deleteDh(DonHang dh);
	public DonHang createDonHang(DonHang dh);
	public List<DonHang> getAlldh();
	void saveDh(DonHang dh);
	void deleteByid(int id);
	 Page <DonHang> findPaginated(int pageNo, int pageSize);
}
