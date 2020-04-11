package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import com.jhndagon.app.jwt.services.IPuntoDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping(value = "/api")
public class PuntoDeVentaController {
    @Autowired
    private IPuntoDeVentaService puntoDeVentaService;

    @PostMapping("/puntoDeVenta")
    @ResponseStatus(HttpStatus.CREATED)
    public PuntoDeVenta crearPuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta ){
        return puntoDeVentaService.createPuntoDeVenta(puntoDeVenta);

    }
    @GetMapping("/puntoDeVenta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PuntoDeVenta puntoDeVenta(@PathVariable Long id){
        return puntoDeVentaService.findById(id);
    }

    @GetMapping("/puntosDeVenta")
    @ResponseStatus(HttpStatus.OK)
    public Page<PuntoDeVenta> puntosDeVenta(@RequestParam(name="page" ,defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") @Max(value = 10) int size){
        Page<PuntoDeVenta> puntosDeVenta=puntoDeVentaService.findAllPuntosDeVenta(page,size);
        return puntosDeVenta;

    }

    @PutMapping("/puntoDeVenta/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PuntoDeVenta updatePuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta,@PathVariable Long id){
        return puntoDeVentaService.updatePuntoDeVenta(puntoDeVenta,id);
    }

    @DeleteMapping("/puntoDeVenta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPuntoDeVenta(@PathVariable Long id){
        puntoDeVentaService.deletePuntoDeVenta(id);

    }


}
