package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Proveedor;
import com.jhndagon.app.jwt.services.IProveedorService;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public Proveedor Proveedor(@PathVariable Long id) {
        return proveedorService.findProveedorById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor updateProveedor(@RequestBody Proveedor provedor,@PathVariable Long id){
        return proveedorService.updateProveedor(provedor,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
    }


}
