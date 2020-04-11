package com.jhndagon.app.jwt.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jhndagon.app.jwt.models.Compra;


public interface ICompra extends PagingAndSortingRepository<Compra, Long>{
	
}
