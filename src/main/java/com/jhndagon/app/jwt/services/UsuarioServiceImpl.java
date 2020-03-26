package com.jhndagon.app.jwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhndagon.app.jwt.models.Rol;
import com.jhndagon.app.jwt.models.Usuario;
import com.jhndagon.app.jwt.repositories.IUsuario;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuario usuario;

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuario.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario usuario1 = this.usuario.findByUsuario(usuario);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario"+usuario+" en el sistema");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario"+usuario+" en el sistema");
		}
		
		
		List<Rol> roles = new ArrayList();
		roles.add(usuario1.getRol());
		List<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario1.getUsuario(), usuario1.getContrasenia(), true, true, true, true, authorities);
	}

}
