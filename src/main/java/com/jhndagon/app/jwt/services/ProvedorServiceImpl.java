package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.Provedor;
import com.jhndagon.app.jwt.repositories.IProvedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service

public class ProvedorServiceImpl implements IProvedorService {


    @Autowired
    private IProvedor provedorRepository;

    @Override
    @Transactional(readOnly = false)
    public Provedor createProvedor(Provedor provedor) {
        return provedorRepository.save(provedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Provedor findProvedorById(Long id) {
       return provedorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public Provedor updateProvedor(Provedor provedor, Long id) {
        Provedor provedorActual= findProvedorById(id);
        provedorActual.setNombre(provedor.getNombre());
        provedorActual.setNit(provedor.getNit());
        provedorActual.setUbicacion(provedor.getUbicacion());
        provedorActual.setTelefono(provedor.getTelefono());
        
        return provedorRepository.save(provedorActual);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProvedor(Long id) {
        provedorRepository.deleteById(id);
    }

	@Override
	public Page<Provedor> findAllProvedores(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return provedorRepository.findAll(pageable);
	}

}
