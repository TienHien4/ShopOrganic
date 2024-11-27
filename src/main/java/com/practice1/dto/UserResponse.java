package com.practice1.dto;


	public class UserResponse {
		private int id;
		private String email;
		private  String username;
		private  String password;
		private String fullName;
		public UserResponse() {
			super();
		}
		public UserResponse(int id, String email, String username, String password, String fullName) {
			super();
			this.id = id;
			this.email = email;
			this.username = username;
			this.password = password;
			this.fullName = fullName;
			
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
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		
	
	}


