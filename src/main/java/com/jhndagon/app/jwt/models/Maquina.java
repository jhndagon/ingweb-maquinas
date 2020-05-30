package com.jhndagon.app.jwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="maquinas")
@Getter
@Setter

public class Maquina implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String marca;
    @NotEmpty
    private String modelo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @NotEmpty
    private String tipo;
    @Transient
    private Integer cantidad;
    
    @PrePersist
    public void prePersist() {
    	fechaCreacion = new Date();
    }

    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7441584757909078961L;
}
