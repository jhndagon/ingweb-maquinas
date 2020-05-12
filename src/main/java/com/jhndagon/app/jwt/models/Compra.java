package com.jhndagon.app.jwt.models;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.NotEmpty;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empleado")
	private Usuario empleado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_maquina")
	private Maquina maquina;
    @NotEmpty
    private double precio;
    @NotEmpty
    private int cantidad;
    @NotNull
    private String reciboCompra;

    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proveedor")
    private Proveedor idProvedor;
	
	@PrePersist
	public void prePersist() {
		fechaCompra = new Date();		
	}
    
    public double getTotal() {
    	return cantidad * precio;
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 285721190454733784L;

}
