package com.jhndagon.app.jwt.services;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Usuario;

public interface IUsuarioService {

	Usuario findById(Long id);
	
	Usuario findByUsuario(String usuario);
	
	Page<Usuario> findAllUsuarios(int page, int size);
	
	Usuario createUsuario(Usuario usuario);
	
	void deleteUsuario(Long id);
	
	Usuario updateUsuario(Usuario usuario);	
	
}
