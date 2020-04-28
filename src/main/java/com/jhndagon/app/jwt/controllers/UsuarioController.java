package com.jhndagon.app.jwt.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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

import com.jhndagon.app.jwt.models.Usuario;
import com.jhndagon.app.jwt.services.IUsuarioService;


@RestController
@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/")
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
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> usuario(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.findById(id);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al acceder a la base de datos"); 
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			response.put("mensaje", "El cliente con id: ".concat(id.toString().concat(", no existe en la base de datos.")));	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		usuario.setContrasenia("");
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> crearUsuario(
			@Valid @RequestBody Usuario usuario,
			BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField()+"' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
			usuario = usuarioService.createUsuario(usuario);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al insetar en la base de datos"); 
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		usuario.setContrasenia("");
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField()+"' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} 
		
		Usuario usuario_ = usuarioService.findById(id);
		if(usuario_ == null) {
			response.put("mensaje", "El cliente con id: ".concat(id.toString().concat(", no existe en la base de datos.")));	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		usuario_.setContrasenia(usuario_.getContrasenia());			
		usuario_.setApellido(usuario.getApellido());
		usuario_.setCorreo(usuario.getCorreo());
		usuario_.setNombre(usuario.getNombre());
		
		try {
			usuario_ = usuarioService.updateUsuario(usuario_);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos"); 
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		usuario_.setContrasenia("");
		return new ResponseEntity<Usuario>(usuario_, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(id);
		
		try {
			usuarioService.deleteUsuario(id);			
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}
