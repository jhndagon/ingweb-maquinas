package com.jhndagon.app.jwt.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.services.ICompraService;

@RestController
@RequestMapping(value = "/api/compras")
public class CompraController {

    @Autowired
    private ICompraService compraService;
    
    @Secured({"ROLE_ADMIN_PUNTO", "ROLE_ADMIN"})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crearcompra(@RequestBody Compra compra) {
        Compra compraNew=null;
        Map<String, Object> response = new HashMap<>();

        try{
            compraNew= compraService.createCompra(compra);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La compra ha sido creada");
        response.put("Compra",compraNew);

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }
    
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_PUNTO"})
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<Compra> compras(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		Page<Compra> compras = compraService.findAllCompra(page, size);
		return compras;
	}

    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_PUNTO"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> compra(@PathVariable Long id) {
        Compra compra=null;
        Map<String, Object> response = new HashMap<>();
        try {
            compra = compraService.findCompraById(id);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(compra==null){
            response.put("mensaje", "La compra con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Compra>(compra, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN_PUNTO"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateCompra(@RequestBody Compra compra,@PathVariable Long id){

        Compra compraActual= compraService.findCompraById(id);
        Compra compraNew =null;
        Map<String, Object> response = new HashMap<>();

        if(compraActual==null){
            response.put("mensaje", "La compra con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            compraNew=compraService.updateCompra(compra,id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","La compra ha sido actualizada");
        response.put("Compra",compraNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

}

    @Secured({"ROLE_ADMIN_PUNTO"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCompra(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            compraService.deleteCompra(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La compra ha sido eliminada");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
     }
	
}
