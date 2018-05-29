package com.spring.mvc.pojo;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.mvc.jsonview.Views;


@Entity
@Table(name="User")
public class User {
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "MY_SEQ")
	@GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "Id", updatable=true, nullable = false,insertable=true)
	private Long id;
	
	@Column
	@JsonView(Views.Public.class)
	String username;
	@Column
	String password;
	@Column
	@JsonView(Views.Public.class)
	String email;
	@Column
	@JsonView(Views.Public.class)
	String phone;
	@Column
	String address;
	
	
	ArrayList<Appointment> appointments = new ArrayList<>();

	public User() {
	}

	public User(String username, String password, String email, String phone, String address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + "]";
	}

}
