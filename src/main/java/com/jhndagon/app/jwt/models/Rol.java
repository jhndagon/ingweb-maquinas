package com.jhndagon.app.jwt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Rol implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(unique= true, length=20)
	private String nombre;
	private String descripcion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2415023130638940409L;
	
	
	
}
