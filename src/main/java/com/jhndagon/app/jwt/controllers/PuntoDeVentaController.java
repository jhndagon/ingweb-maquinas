package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import com.jhndagon.app.jwt.models.Usuario;
import com.jhndagon.app.jwt.services.IPuntoDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/puntos-de-ventas")
public class PuntoDeVentaController {
    @Autowired
    private IPuntoDeVentaService puntoDeVentaService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<?> crearPuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta ){
        PuntoDeVenta puntoDeVentaNew=null;
        Map<String, Object> response = new HashMap<>();

        try{
            puntoDeVentaNew=puntoDeVentaService.createPuntoDeVenta(puntoDeVenta);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El punto de venta ha sido creado");
        response.put("Punto de venta",puntoDeVentaNew);


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }

    @Secured({"ROLE_RECURSO_HUMANO", "ROLE_ADMIN"})
    @GetMapping("/")
    public Page<PuntoDeVenta> puntosDeVenta(@RequestParam(name="page" ,defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") @Max(value = 10) int size){
        Page<PuntoDeVenta> puntosDeVenta=puntoDeVentaService.findAllPuntosDeVenta(page,size);
        return puntosDeVenta;

    }

    @Secured({"ROLE_RECURSO_HUMANO", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>puntoDeVenta(@PathVariable Long id){
        PuntoDeVenta puntoDeVenta=null;
        Map<String, Object> response = new HashMap<>();

        try {
            puntoDeVenta=puntoDeVentaService.findById(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(puntoDeVenta==null){
            response.put("mensaje", "El punto de venta con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PuntoDeVenta>(puntoDeVenta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta,@PathVariable Long id){

        PuntoDeVenta puntoDeVentaActual= puntoDeVentaService.findById(id);
        PuntoDeVenta puntoDeVentaUp =null;
        Map<String, Object> response = new HashMap<>();

        if(puntoDeVentaActual==null){
            response.put("mensaje", "El punto de venta con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            puntoDeVentaUp=puntoDeVentaService.updatePuntoDeVenta(puntoDeVenta,id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El punto de venta ha sido actualizado");
        response.put("Punto de venta",puntoDeVentaUp);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPuntoDeVenta(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try {
            puntoDeVentaService.deletePuntoDeVenta(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El punto de venta ha sido eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
