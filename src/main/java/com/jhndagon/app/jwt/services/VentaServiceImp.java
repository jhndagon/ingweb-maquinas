package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhndagon.app.jwt.models.Venta;
import com.jhndagon.app.jwt.repositories.IVenta;


@Service
public class VentaServiceImp implements IVentaService{
	
	@Autowired
	private IVenta ventaRepository;


	@Override
	public Page<Venta> findAllVenta(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return ventaRepository.findAll(pageable);
	}

	@Override
    @Transactional(readOnly = false)
	public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
	}

	@Override
    @Transactional(readOnly = true)
	public Venta findVentaById(Long id) {
	       return ventaRepository.findById(id).orElse(null);
	}

	@Override
    @Transactional(readOnly = false)
	public Venta updateVenta(Venta venta, Long id) {
        Venta ventaActual= findVentaById(id);
        ventaActual.setPrecio(venta.getPrecio());
        ventaActual.setCantidad(venta.getCantidad());
        ventaActual.setFechaVenta(venta.getFechaVenta());
        
        return ventaRepository.save(ventaActual);
	}

	@Override
    @Transactional(readOnly = false)
	public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
		
	}

	@Override
	public List<Venta> findByPuntoVenta(Long idPuntoVenta) {
		return ventaRepository.findByPuntoVenta(idPuntoVenta);
	}

}
