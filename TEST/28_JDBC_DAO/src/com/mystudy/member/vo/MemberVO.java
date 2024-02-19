package com.mystudy.member.vo;

public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String phone;
	private String address;
	
	public MemberVO(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public MemberVO(String id, String name, String password, String phone, String address) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", address="
				+ address + "]";
	}
	
	
}
