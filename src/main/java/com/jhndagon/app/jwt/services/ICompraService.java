package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Compra;


public interface ICompraService {
	
	Page<Compra> findAllCompra(int page, int size);
	
	List<Compra> findByPuntoVenta(Long idPuntoVenta);
	
    Compra createCompra(Compra compra);

    Compra findCompraById(Long id);

    Compra updateCompra(Compra compra, Long id);
    
    

    void deleteCompra(Long id);
}
