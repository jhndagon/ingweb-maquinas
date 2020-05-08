package com.jhndagon.app.jwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ventas")
@Getter
@Setter

public class Venta implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5357137932113350964L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double precio;
    @NotNull
    private int cantidad;
    
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
	
}
