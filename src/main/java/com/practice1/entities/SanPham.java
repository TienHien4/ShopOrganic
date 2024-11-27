package com.practice1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SanPham")
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int id;
	
	private String photo;
	
	@Column(name="nameProduct")
	private String nameProduct;
	
	@Column(name="productInformation")
	private String productInformation;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private double price;
	
	@Column(name="sale")
	private double percentSale;
	

	public SanPham() {
		super();
	}

	public SanPham(int id, String photo, String nameProduct, String productInformation, double weight, String type,
			double price, double percentSale) {
		super();
		this.id = id;
		this.photo = photo;
		this.nameProduct = nameProduct;
		this.productInformation = productInformation;
		this.weight = weight;
		this.type = type;
		this.price = price;
		this.percentSale = percentSale;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getProductInformation() {
		return productInformation;
	}

	public void setProductInformation(String productInformation) {
		this.productInformation = productInformation;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPercentSale() {
		return percentSale;
	}

	public void setPercentSale(double percentSale) {
		this.percentSale = percentSale;
	}
	
	
	
	
	
	


}
