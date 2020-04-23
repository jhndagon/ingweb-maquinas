package com.jhndagon.app.jwt.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jhndagon.app.jwt.models.Usuario;

@Repository
public interface IUsuario extends PagingAndSortingRepository<Usuario, Long>{

	Usuario findByUsuario(String usuario);

	
}
