package com.amit.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Size(min=4, max=20, message = "Name size must be between 4 and 20 characters")
	private String name;
	@NotEmpty(message= "Phone no can not be empty")
	private String phoneNo;
	private String city;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, String name, String phoneNo, String city, List<Account> accounts) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.city = city;
		this.accounts = accounts;
	}
	
	public Customer( String name, String phoneNo, String city, List<Account> accounts) {

		this.name = name;
		this.phoneNo = phoneNo;
		this.city = city;
		this.accounts = accounts;
	}


	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@OneToMany(targetEntity = Account.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="userCustomerId", referencedColumnName = "customerId")
	private List<Account> accounts;
}
