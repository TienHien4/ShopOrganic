package com.practice1.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name = "CartItem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="item_id")
	private int id;
	@Column(name="quantity")
	private int quantity;
	@Column(name="totalPrice")
	private double totalPrice;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private SanPham sp;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ShoppingCart_Id", referencedColumnName = "ShoppingCart_Id")
	private ShoppingCart cart;
	public CartItem() {
		super();
	}
	public CartItem(int id, int quantity, double totalPrice, SanPham sp, ShoppingCart cart) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.sp = sp;
		this.cart = cart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public SanPham getSp() {
		return sp;
	}
	public void setSp(SanPham sp) {
		this.sp = sp;
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	
}
