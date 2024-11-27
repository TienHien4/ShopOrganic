package com.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.practice1.entities.DonHang;


public interface DonHangRepository extends CrudRepository<DonHang, Integer>, JpaRepository<DonHang, Integer> {

}
