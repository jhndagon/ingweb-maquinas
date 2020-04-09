package com.jhndagon.app.jwt.controllers;


import java.util.List;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Rol;
import com.jhndagon.app.jwt.models.Usuario;
import com.jhndagon.app.jwt.services.IUsuarioService;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public Page<Usuario> usuarios(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		return usuarioService.findAllUsuarios(page, size);
	}
	
	//@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuario/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario usuario(@PathVariable Long usuarioId) {
		return usuarioService.findById(usuarioId);
	}
	
	//@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		return usuarioService.createUsuario(usuario);
	}
	
	//@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/usuario/{id}")
	public Usuario borrarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		usuarioService.deleteUsuario(id);
		return usuario;
	}
	
	@GetMapping("/roles")
	public List<Rol> obtenerRoles() {
		return usuarioService.getRoles();
	}
	
	
	
}
