package com.jhndagon.app.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jhndagon.app.jwt.models.Rol;
import com.jhndagon.app.jwt.models.Usuario;

@Repository
public interface IUsuario extends PagingAndSortingRepository<Usuario, Long>{

	Usuario findByUsuario(String usuario);
	
	@Query("from Rol")
	List<Rol> getRoles();
	
}
