package com.jhndagon.app.jwt.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="compras")
@Getter
@Setter
public class Compra implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double precio;
    @NotNull
    private int cantidad;
    @NotNull
    private String reciboCompra;

    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @NotNull
    private long idProvedor;

	/**
	 * 
	 */
	private static final long serialVersionUID = 285721190454733784L;

}
