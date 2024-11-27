package com.practice1.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ShoppingCart_Id")
	private int id;
	@Column(name="totalItems")
	private int totalItems;
	@Column(name="totalPrices")
	private double totalPrices;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cart")
	private Set<CartItem> cartItem;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	public ShoppingCart() {
		super();
	}
	public ShoppingCart(int id, int totalItems, double totalPrices, Set<CartItem> cartItem, Customer customer) {
		super();
		this.id = id;
		this.totalItems = totalItems;
		this.totalPrices = totalPrices;
		this.cartItem = cartItem;
		this.customer = customer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public double getTotalPrices() {
		return totalPrices;
	}
	public void setTotalPrices(double totalPrices) {
		this.totalPrices = totalPrices;
	}
	public Set<CartItem> getCartItem() {
		return cartItem;
	}
	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
