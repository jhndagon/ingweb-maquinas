package com.jhndagon.app.jwt.services;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Proveedor;


public interface IProveedorService {

	Page<Proveedor> findAllProveedores(int page, int size);
	
    Proveedor createProveedor(Proveedor proveedor);

    Proveedor findProveedorById(Long id);    

    Proveedor updateProveedor(Proveedor proveedor, Long id);

    void deleteProveedor(Long id);
}
