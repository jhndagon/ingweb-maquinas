package com.jhndagon.app.jwt.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="contratos")
@Getter
@Setter
public class Contrato implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@NotEmpty
	public String nombre;
	public String descripcion;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9104632145304758864L;

}
