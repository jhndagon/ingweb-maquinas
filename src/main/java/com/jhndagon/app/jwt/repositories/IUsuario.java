package com.jhndagon.app.jwt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhndagon.app.jwt.models.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long>{

	public Usuario findByUsuario(String usuario);
}
