package com.jhndagon.app.jwt.services;

import org.springframework.data.domain.Page;

import com.jhndagon.app.jwt.models.Provedor;


public interface IProvedorService {

    Provedor createProvedor(Provedor provedor);

    Provedor findProvedorById(Long id);
    
	Page<Provedor> findAllProvedores(int page, int size);

    Provedor updateProvedor(Provedor provedor, Long id);

    void deleteProvedor(Long id);
}
