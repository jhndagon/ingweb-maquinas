package com.jhndagon.app.jwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="provedores")
@Getter
@Setter

public class Provedor implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String nit;
    @NotNull
    private String ubicacion;
    @NotNull
    private String telefono;
    
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -7441584757909078961L;
}
