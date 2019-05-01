package com.neu.edu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int TransactionId;
	@ManyToOne()
	@JoinColumn(name="CustomerID", nullable=false)
	private Customer customer;
	@Column(name="AccountNumber",nullable=false)
	private int accountNumber;
	@Column(name="", nullable=false)
	private double amount;
	@Column(name="TransactionType", nullable=false)
	private String transactionType;
	@Column(name="DateTime", nullable=false)
	private Date DateTime;
	
	public int getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getDateTime() {
		return DateTime;
	}
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}
}
