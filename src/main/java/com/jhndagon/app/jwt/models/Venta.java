package com.jhndagon.app.jwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ventas")
@Getter
@Setter

public class Venta implements Serializable {

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
    
    private String nombre;
    private String apellido;
    private String cedula;
    private String celular;
    
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    
	@PrePersist
	public void prePersist() {
		fechaVenta = new Date();		
	}
    
    
    public double getTotal() {
    	return cantidad * precio;
    }
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5357137932113350964L;
}
