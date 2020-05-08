package com.jhndagon.app.jwt.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jhndagon.app.jwt.models.Venta;


public interface IVenta extends PagingAndSortingRepository<Venta, Long>{
	
}
