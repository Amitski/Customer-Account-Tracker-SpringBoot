package com.amit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import org.springframework.stereotype.Component;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	private double amount;
	private int senderAccountNo;
	private int receiverAccountNo;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int transactionId, double amount, int senderAccountNo, int receiverAccountNo) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.senderAccountNo = senderAccountNo;
		this.receiverAccountNo = receiverAccountNo;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getSenderAccountNo() {
		return senderAccountNo;
	}
	public void setSenderAccountNo(int senderAccountNo) {
		this.senderAccountNo = senderAccountNo;
	}
	public int getReceiverAccountNo() {
		return receiverAccountNo;
	}
	public void setReceiverAccountNo(int receiverAccountNo) {
		this.receiverAccountNo = receiverAccountNo;
	}
	
	
	

}
