package com.wellsfargo.sba3.its.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class Users implements Serializable{
	
	@Id
	@Column(name="userId",unique=true)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="firstName")
	@NotBlank(message="First Name can not be Omitted")
	@NotNull(message="First Name cannot be Omitted")
	@Size(min=5, max =30, message="First Name should be characters of length between 5 and 30")
	private String firstName;
	
	@Column(name="lastName")
	@NotBlank(message="Last Name cannot be Omitted")
	@NotNull(message="Last Name cannot be Omitted")
	@Size(min=3, max=25,message="Last Name should be characters of length between 5 and 30")
	private String lastName;
	
	@Column(name="email")
	@NotBlank(message="email cannot be Omitted")
	@NotNull(message="email cannot be Omitted")
	@Email(message="Email should be an valid emailID")
	private String email;
	
	@Column(name="mobile")
	@NotBlank(message="Mobile Number cannot be Omitted")
	@NotNull(message="Mobile Number cannot be Omitted")
	@Size(min=10,max=10,message = "Mobile should be of 10 chars")
	@Pattern(regexp="(^$|[0-9]{10})", message="Mobile Number should have the number till max 10")
	private String mobile;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Users(int userId, String firstName, String lastName,String email, String mobile) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}

	public Users() {
		
	}
}