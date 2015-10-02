package com.erp.myapp.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private boolean actived;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1,Maximum : 30")
		private String firstName;
	
	@Column(nullable=false) 
	@Size(min=1,max=50, message="Minimum: 1,Maximum : 50")
		private String lastName;
	
	@Column(unique=true, nullable=false)
	@Size(min=2,max=14, message="Minimum : 2 , Maximum 14")
		private String userName;
	
	@Column(unique=true, nullable=false)
	@Size(min=1, message="Ce champ ne peut être vide")
	@Email
		private String email;
	
	@Column(nullable=false)
	@Size(min=2,max=40, message="Le mot de passe doit faire minimum 2 caractères")
		private String password;
	
	@Transient
	private String confirmPassword;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Collection<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
