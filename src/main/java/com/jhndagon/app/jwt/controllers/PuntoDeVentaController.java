package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import com.jhndagon.app.jwt.services.IPuntoDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping(value = "/api/puntos-de-ventas")
public class PuntoDeVentaController {
    @Autowired
    private IPuntoDeVentaService puntoDeVentaService;
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PuntoDeVenta crearPuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta ){
        return puntoDeVentaService.createPuntoDeVenta(puntoDeVenta);

    }
  
    @Secured({"ROLE_RECURSO_HUMANO", "ROLE_ADMIN"})
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<PuntoDeVenta> puntosDeVenta(@RequestParam(name="page" ,defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") @Max(value = 10) int size){
        Page<PuntoDeVenta> puntosDeVenta=puntoDeVentaService.findAllPuntosDeVenta(page,size);
        return puntosDeVenta;

    }
    
    @Secured({"ROLE_RECURSO_HUMANO", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PuntoDeVenta puntoDeVenta(@PathVariable Long id){
        return puntoDeVentaService.findById(id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PuntoDeVenta updatePuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta,@PathVariable Long id){
        return puntoDeVentaService.updatePuntoDeVenta(puntoDeVenta,id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPuntoDeVenta(@PathVariable Long id){
        puntoDeVentaService.deletePuntoDeVenta(id);

    }


}
