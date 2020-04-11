package com.jhndagon.app.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Contrato;
import com.jhndagon.app.jwt.services.IContratoService;

@RestController
@RequestMapping("/api")
public class ContratoController {

	@Autowired
	private IContratoService contratoService;
	
	@GetMapping("/contratos")
	public List<Contrato> getContratos(){
		return contratoService.getContratos();
	}
}
