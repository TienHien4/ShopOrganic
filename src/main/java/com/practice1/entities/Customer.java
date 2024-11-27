package com.practice1.entities;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "NguoiDung")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "customer_id")
	private int id;
	@Column
	private String fullName;
	@Column
	private String sex;
	@Column
	private String address;
	@Column
	private String phoneNumber;
	@Column
	private String email;
	@Column
	private String username;
	@Column
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name ="customer_id", referencedColumnName = "customer_id"),
	   inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> vaitro;
	
	
    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;    
	

	public Customer() {
		super();
	}


	public Customer(int id, String fullName, String sex, String address, String phoneNumber, String email, String username,
			String password, Collection<Role> vaitro, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.sex = sex;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.vaitro = vaitro;
		this.shoppingCart = shoppingCart;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Role> getVaitro() {
		return vaitro;
	}


	public void setVaitro(Collection<Role> vaitro) {
		this.vaitro = vaitro;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	

	
	

}