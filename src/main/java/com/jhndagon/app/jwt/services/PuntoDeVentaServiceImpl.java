package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.PuntoDeVenta;
import com.jhndagon.app.jwt.repositories.IPuntoDeVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PuntoDeVentaServiceImpl implements IPuntoDeVentaService{
    @Autowired
    private IPuntoDeVenta puntoDeVentaRepository;

    @Override
    @Transactional(readOnly = false)
    public PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
        return puntoDeVentaRepository.save(puntoDeVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public PuntoDeVenta findById(Long id) {
        return puntoDeVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Maquina no registrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PuntoDeVenta> findAllPuntosDeVenta(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return puntoDeVentaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public PuntoDeVenta updatePuntoDeVenta(PuntoDeVenta puntoDeVenta, Long id) {
        PuntoDeVenta puntoDeVentaActual=findById(id);
        puntoDeVentaActual.setNombre(puntoDeVenta.getNombre());
        puntoDeVentaActual.setDetalle(puntoDeVenta.getDetalle());
        puntoDeVentaActual.setDireccion(puntoDeVenta.getDireccion());
        puntoDeVentaActual.setTelefono(puntoDeVenta.getTelefono());
        return puntoDeVentaRepository.save(puntoDeVentaActual);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePuntoDeVenta(Long id) {
        puntoDeVentaRepository.deleteById(id);
    }
}
