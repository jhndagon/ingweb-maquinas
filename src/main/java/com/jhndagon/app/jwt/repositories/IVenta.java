package com.jhndagon.app.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jhndagon.app.jwt.models.Venta;


public interface IVenta extends PagingAndSortingRepository<Venta, Long>{
	
	@Query("SELECT v FROM Venta v JOIN v.empleado e Join e.puntoVenta pv where pv.id = ?1")
	List<Venta> findByPuntoVenta(Long idPuntoVenta);

	@Query("SELECT v FROM Venta v JOIN v.empleado e Join e.puntoVenta pv JOIN v.maquina m where pv.id = ?1  and m.id=?1 ")
	List<Venta> findByPuntoVentaMaquina(Long idPuntoVenta,Long marca);

}
