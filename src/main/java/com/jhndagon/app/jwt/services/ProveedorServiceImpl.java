package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.Proveedor;
import com.jhndagon.app.jwt.repositories.IProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service

public class ProveedorServiceImpl implements IProveedorService {


    @Autowired
    private IProveedor provedorRepository;


	@Override
	public Page<Proveedor> findAllProveedores(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return provedorRepository.findAll(pageable);
	}
	
    @Override
    @Transactional(readOnly = false)
    public Proveedor createProveedor(Proveedor proveedor) {
        return provedorRepository.save(proveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor findProveedorById(Long id) {
       return provedorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public Proveedor updateProveedor(Proveedor proveedor, Long id) {
        Proveedor provedorActual= findProveedorById(id);
        provedorActual.setNombre(proveedor.getNombre());
        provedorActual.setNit(proveedor.getNit());
        provedorActual.setUbicacion(proveedor.getUbicacion());
        provedorActual.setTelefono(proveedor.getTelefono());
        
        return provedorRepository.save(provedorActual);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProveedor(Long id) {
        provedorRepository.deleteById(id);
    }


}
