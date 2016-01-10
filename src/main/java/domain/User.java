package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class User extends DomainEntity {

	public User() {
		super();
	}

	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
//	private byte[] photo;

	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	//Pattern
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
//	@Column(columnDefinition = "LONGBLOB")
//	public byte[] getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(byte[] photo) {
//		this.photo = photo;
//	}

	private UserAccount userAccount;

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public String toString(){
		return "name = " + getName() + "surname = "+ getSurname()  + ", email= " + email;
	}

}