package com.jhndagon.app.jwt.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
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
	@NotEmpty
	private String nombre;
	private String detalle;
	@NotNull
	private String direccion;
	@NotNull
	private String telefono;
	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197340963250033103L;
	
}
