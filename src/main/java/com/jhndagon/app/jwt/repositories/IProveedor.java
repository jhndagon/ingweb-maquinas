package com.jhndagon.app.jwt.repositories;

import com.jhndagon.app.jwt.models.Proveedor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedor extends PagingAndSortingRepository<Proveedor, Long> {
	
}
