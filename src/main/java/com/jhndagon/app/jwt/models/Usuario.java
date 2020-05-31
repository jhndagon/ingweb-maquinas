package com.jhndagon.app.jwt.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="usuarios")
@Getter
@Setter
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3240927467102198294L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nombre;
	private String apellido;
	@NotEmpty
	@Column(unique = true, length = 20)
	private String usuario;
	@Column(length = 100)
	private String contrasenia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol")
	private Rol rol;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contrato")
	private Contrato contrato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_punto_venta")
	private PuntoDeVenta puntoVenta;
	@Email
	private String correo;
	@Temporal(TemporalType.TIME)
	private Date fechaInicio;
	@Temporal(TemporalType.TIME)
	private Date fechaFin;
	@Hidden
	private String foto;
	@Hidden
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
		
	}


}
