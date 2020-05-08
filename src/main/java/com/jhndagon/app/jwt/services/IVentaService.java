package com.jhndagon.app.jwt.services;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Venta;


public interface IVentaService {
	
	Page<Venta> findAllVenta(int page, int size);
	
    Venta createVenta(Venta venta);

    Venta findVentaById(Long id);

    Venta updateVenta(Venta venta, Long id);

    void deleteVenta(Long id);
}
