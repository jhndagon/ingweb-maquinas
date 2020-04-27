package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Proveedor;
import com.jhndagon.app.jwt.services.IProveedorService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({"ROLE_ADMIN", "ROLE_ADMIN_PUNTO"})
@RequestMapping(value = "/api/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor crearProveedor(@RequestBody Proveedor provedor) {
        return proveedorService.createProveedor(provedor);
    }
    
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<Proveedor> provedores(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		Page<Proveedor> provedores = proveedorService.findAllProveedores(page, size);
		return provedores;
	}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> Proveedor(@PathVariable Long id) {

		Proveedor proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedorService.findProveedorById(id);
		}
		catch (DataAccessException e){
			response.put("mensaje", "Error al acceder a la base de datos"); 
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		if(proveedor == null) {
			response.put("mensaje", "El proveedor con id: ".concat(id.toString().concat(", no existe en la base de datos.")));	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  updateProveedor(@RequestBody Proveedor proveedor,@PathVariable Long id){

        Proveedor proveedorActual= proveedorService.findProveedorById(id);
        Proveedor proveedorNew = null;
        Map<String, Object> response = new HashMap<>();

        if(proveedorActual==null){
            response.put("mensaje", "El proveedor con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            proveedorNew=proveedorService.updateProveedor(proveedor,id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El proveedor ha sido actualizado");
        response.put("Proveedor",proveedorNew);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
        	proveedorService.deleteProveedor(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El proveedor ha sido eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
