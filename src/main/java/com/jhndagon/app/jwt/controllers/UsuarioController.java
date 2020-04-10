package com.jhndagon.app.jwt.controllers;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		Page<Usuario> usuarios = usuarioService.findAllUsuarios(page, size);
		usuarios.getContent().stream().map(usuario -> {
			usuario.setContrasenia("");
			return usuario;
		}).collect(Collectors.toList());
		return usuarios;
	}
	
	//@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario usuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		if(usuario != null) {
			usuario.setContrasenia("");			
		}
		return usuario;
	}
	
	//@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		usuario = usuarioService.createUsuario(usuario);
		usuario.setContrasenia("");
		return usuario;
	}
	
	//@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuario_ = usuarioService.findById(id);
		if(!usuario.getContrasenia().equals(usuario_.getApellido())) {
			usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));			
		}
		usuario_ = usuarioService.updateUsuario(usuario_);
		usuario_.setContrasenia("");
		return usuario_;
	}
	
	//@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/usuario/{id}")
	public Usuario borrarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		usuarioService.deleteUsuario(id);
		return usuario;
	}
	
	//@Secured({"ROLE_ADMIN"})
	@GetMapping("/roles")
	public List<Rol> obtenerRoles() {
		SecurityContext secury = SecurityContextHolder.getContext();
		List<Rol> roles = usuarioService.getRoles();
		if(secury == null || 
				!secury.getAuthentication().getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN")) {
			roles = roles.stream().filter(rol -> !rol.getNombre().equals("ROLE_ADMIN")).collect(Collectors.toList());
		} 
		return roles;
	}	
}
