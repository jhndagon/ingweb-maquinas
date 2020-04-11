package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import org.springframework.data.domain.Page;

public interface IPuntoDeVentaService {
    PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta);

    PuntoDeVenta findById(Long id);

    Page<PuntoDeVenta> findAllPuntosDeVenta(int page, int size);

    PuntoDeVenta updatePuntoDeVenta(PuntoDeVenta puntoDeVenta, Long id);

    void deletePuntoDeVenta(Long id);
}
