package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.repositories.ICompra;


@Service
public class CompraServiceImp implements ICompraService{
	
	@Autowired
	private ICompra compraRepository;


	@Override
	public Page<Compra> findAllCompra(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return compraRepository.findAll(pageable);
	}

	@Override
    @Transactional(readOnly = false)
	public Compra createCompra(Compra compra) {
        return compraRepository.save(compra);
	}

	@Override
    @Transactional(readOnly = true)
	public Compra findCompraById(Long id) {
	       return compraRepository.findById(id).orElse(null);
	}

	@Override
    @Transactional(readOnly = false)
	public Compra updateCompra(Compra compra, Long id) {
        Compra compraActual= findCompraById(id);
        compraActual.setPrecio(compra.getPrecio());
        compraActual.setCantidad(compra.getCantidad());
        compraActual.setReciboCompra(compra.getReciboCompra());
        compraActual.setFechaCompra(compra.getFechaCompra());
        
        return compraRepository.save(compraActual);
	}

	@Override
    @Transactional(readOnly = false)
	public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
		
	}

	@Override
	public List<Compra> findByPuntoVenta(Long idPuntoVenta) {
		return compraRepository.findByPuntoVenta(idPuntoVenta);
	}

}
