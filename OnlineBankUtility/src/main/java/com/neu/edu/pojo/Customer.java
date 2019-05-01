package com.neu.edu.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerID;
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Email Address Not Valid")
	@Column(name="Email",unique=true)
	private String customerEmail;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Address")
	private String customerAddress;
	@Column(name="Phone")
	private String phoneNumber;
	@Transient
	private MultipartFile photo;
	@Column(name="PhotoFile")
	private String photoFile;
//	@OneToOne
//	@JoinColumn(name="RoleId", nullable = false)
//	private Role role;
	@Column(name ="pwd")
	private String customerPassword;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
	private List<AccountDetails> accountDetails = new ArrayList<AccountDetails>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
	private List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();
	
//	public Role getRole() {
//		return role;
//	}
//	public void setRole(Role role) {
//		this.role = role;
//	}
	public List<AccountDetails> getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(List<AccountDetails> accountDetails) {
		this.accountDetails = accountDetails;
	}
	public List<TransactionDetails> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<TransactionDetails> transaction) {
		this.transaction = transaction;
	}
	public String getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
}
