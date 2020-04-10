package com.jhndagon.app.jwt.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="puntos_de_venta")
@Getter
@Setter
public class PuntoDeVenta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197340963250033103L;
	
}
