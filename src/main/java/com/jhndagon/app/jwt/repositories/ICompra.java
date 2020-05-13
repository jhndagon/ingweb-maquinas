package com.jhndagon.app.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jhndagon.app.jwt.models.Compra;

@Repository
public interface ICompra extends PagingAndSortingRepository<Compra, Long>{
	
	@Query("SELECT c FROM Compra c JOIN c.empleado e Join e.puntoVenta pv where pv.id = ?1")
	List<Compra> findByPuntoVenta(Long idPuntoVenta);
	
}
