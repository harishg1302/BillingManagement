package com.hnm.billing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;

@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1234567L;
	@Id
	private int id;
	private String  name;
	private String  mobileNumber;
	private String  email;
	private String  address;
	@Field(targetType = FieldType.STRING)
	private Role    role;
	private boolean status;
	private String  password;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getMobileNumber() {

		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {

		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public Role getRole() {

		return role;
	}

	public void setRole(Role role) {

		this.role = role;
	}

	public boolean isStatus() {

		return status;
	}

	public void setStatus(boolean status) {

		this.status = status;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}
