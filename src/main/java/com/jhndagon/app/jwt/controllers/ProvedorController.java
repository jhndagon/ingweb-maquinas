package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Provedor;
import com.jhndagon.app.jwt.services.IProvedorService;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/proveedores")
public class ProvedorController {

    @Autowired
    private IProvedorService provedorService;
    
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<Provedor> provedores(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		Page<Provedor> provedores = provedorService.findAllProvedores(page, size);
		return provedores;
	}

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Provedor crearProvedor(@RequestBody Provedor provedor) {
        return provedorService.createProvedor(provedor);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provedor Provedor(@PathVariable Long id) {
        return provedorService.findProvedorById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Provedor updateProvedor(@RequestBody Provedor provedor,@PathVariable Long id){
        return provedorService.updateProvedor(provedor,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProvedor(@PathVariable Long id) {
        provedorService.deleteProvedor(id);
    }


}
