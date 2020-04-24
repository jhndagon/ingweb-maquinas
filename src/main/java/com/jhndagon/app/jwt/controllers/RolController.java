package com.jhndagon.app.jwt.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Rol;
import com.jhndagon.app.jwt.services.IRolService;

@RestController
@RequestMapping("/api/roles")
@Secured({"ROLE_ADMIN", "ROLE_RECURSO_HUMANO"})
public class RolController {
	

	@Autowired
	private IRolService rolService;
	
	@GetMapping("/")
	public List<Rol> obtenerRoles(){
		SecurityContext secury = SecurityContextHolder.getContext();
		List<Rol> roles = rolService.getRoles();
		if(secury == null || 
				!secury.getAuthentication().getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN")) {
			roles = roles.stream().filter(rol -> !rol.getNombre().equals("ROLE_ADMIN")).collect(Collectors.toList());
		} 
		return roles;
	}
	
	
}
