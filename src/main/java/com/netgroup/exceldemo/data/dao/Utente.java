package com.netgroup.exceldemo.data.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "username", unique = true)
	@NotBlank @NotEmpty @NotNull
 	private String username; 

	@Column(name = "firstname")
	@NotBlank @NotEmpty @NotNull
	private String firstName;

	@Column(name = "lastname")
	@NotBlank @NotEmpty @NotNull
	private String lastName;

	@Column(name = "email")
	@NotBlank @NotEmpty @NotNull
	private String email;

	@Column(name = "password")
	@NotBlank
	@NotEmpty
	@NotNull
	private String password;
	
	@Column(name = "role")
	@NotBlank
	@NotEmpty
	@NotNull
	private String role;
	
	

	
}
