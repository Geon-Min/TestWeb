package com.testweb.user.model;

public class UserVO {
	
	private String id;
	private String name;
	private String passWord;
	private String email;
	private String phone;
	private String address;
	private String address2;
	
	public UserVO() {
		
	}

	public UserVO(String id, String name, String passWord, String email, String phone, String address, String address2) {
		super();
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.address2 = address2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	

}
