package com.jhndagon.app.jwt.services;

import java.util.List;

import com.jhndagon.app.jwt.models.Rol;

public interface IRolService {

	List<Rol> getRoles();
	
	Rol getRolById(Integer rol);
}
