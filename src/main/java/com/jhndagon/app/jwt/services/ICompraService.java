package com.jhndagon.app.jwt.services;

import java.util.List;

import com.jhndagon.app.jwt.models.Compra;


public interface ICompraService {
	
	List<Compra> findAllCompra();
	
    Compra createCompra(Compra compra);

    Compra findCompraById(Long id);

    Compra updateCompra(Compra compra, Long id);

    void deleteMaquina(Long id);
}
