package com.jhndagon.app.jwt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
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
    
<<<<<<< HEAD

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<Compra> compras(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		Page<Compra> compras = compraService.findAllCompra(page, size);
		return compras;
	}

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Compra crearProveedor(@RequestBody Compra compra) {
        return compraService.createCompra(compra);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Compra Compra(@PathVariable Long id) {
        return compraService.findCompraById(id);
    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Compra updateCompra(@RequestBody Compra provedor,@PathVariable Long id){
//        return compraService.updateCompra(provedor,id);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCompra(@PathVariable Long id) {
    	compraService.deleteCompra(id);
=======
    @GetMapping("/")
	public List<Compra> compras(){
			
		return compraService.findAllCompra();
>>>>>>> d24fccd910a05697a95be11bd1de305c3bf9f94f
    }
	
}
