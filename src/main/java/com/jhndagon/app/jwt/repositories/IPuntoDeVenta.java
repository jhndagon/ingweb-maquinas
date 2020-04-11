package com.jhndagon.app.jwt.repositories;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuntoDeVenta extends PagingAndSortingRepository<PuntoDeVenta,Long> {
}
