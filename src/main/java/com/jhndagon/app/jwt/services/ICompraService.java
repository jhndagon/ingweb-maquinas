package com.jhndagon.app.jwt.services;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Compra;


public interface ICompraService {
	
	Page<Compra> findAllCompra(int page, int size);
	
    Compra createCompra(Compra compra);

    Compra findCompraById(Long id);

    Compra updateCompra(Compra compra, Long id);

    void deleteCompra(Long id);
}
