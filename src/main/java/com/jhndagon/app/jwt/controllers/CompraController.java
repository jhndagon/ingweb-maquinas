package com.jhndagon.app.jwt.controllers;

import java.util.List;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.services.ICompraService;

@RestController
@RequestMapping(value = "/api")
public class CompraController {


    @Autowired
    private ICompraService compraService;
    
    @GetMapping("/compras")
	public List<Compra> compras(){
			
		return compraService.findAllCompra();
    }
	
}
