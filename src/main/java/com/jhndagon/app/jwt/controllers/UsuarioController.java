package com.jhndagon.app.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.services.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public String usuarios() {
		return "hola usuarios";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario login";
	}
	
}
