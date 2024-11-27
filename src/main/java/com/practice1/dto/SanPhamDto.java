package com.practice1.dto;


import org.springframework.web.multipart.MultipartFile;

public class SanPhamDto {
	private int id;

	private MultipartFile photo;

	private String nameProduct;

	private String productInformation;

	private double weight;

	private String type;

	private double price;
	
	private double percentSale;
	

	public SanPhamDto() {
		super();
	}


	public SanPhamDto(int id, MultipartFile photo, String nameProduct, String productInformation, double weight,
			String type, double price, double percentSale) {
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


	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
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


	public double getPercentSale() {
		return percentSale;
	}


	public void setPercentSale(double percentSale) {
		this.percentSale = percentSale;
	}

	
	

}
