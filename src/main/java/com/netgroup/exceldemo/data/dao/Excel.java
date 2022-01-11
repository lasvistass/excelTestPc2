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
@Table(name = "excel_prodotto")
public class Excel {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome_prodotto")
	@NotBlank
	@NotEmpty
	@NotNull
	private String nomeProdotto;

	@Column(name = "categoria_prodotto")
	@NotBlank
	@NotEmpty
	@NotNull
	private String categoriaProdotto;

	@Column(name = "prezzo")
	@NotNull
	private double prezzo;
	

}
