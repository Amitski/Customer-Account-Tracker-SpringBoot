package com.amit.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames= {"bankName", "accountType", "userCustomerId"}))
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNo;
	
	@NotEmpty(message= "Bank name can not be empty")
	private String bankName;
	
	@NotEmpty(message= "Account type can not be empty")
	private String accountType;
	
	@Min(value= 1000, message = "Balance should be greater than or equal to 1000")
	private double balance;
	
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountNo, String bankName, String accountType, double balance) {
		super();
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	public Account( String bankName, String accountType, double balance) {
		this.bankName = bankName;
		this.accountType = accountType;
		this.balance = balance;
	}
	
}

