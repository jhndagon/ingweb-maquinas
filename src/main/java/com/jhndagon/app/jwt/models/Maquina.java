package com.jhndagon.app.jwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @NotNull
    private String tipo;

}
