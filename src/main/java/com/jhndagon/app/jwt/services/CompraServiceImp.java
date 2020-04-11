package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.repositories.ICompra;


@Service
public class CompraServiceImp implements ICompraService{
	
	@Autowired
	private ICompra compraRepository;

	@Override
    @Transactional(readOnly = true)
	public List<Compra> findAllCompra() {		
		return (List<Compra>) compraRepository.findAll(); 
	}

	@Override
    @Transactional(readOnly = false)
	public Compra createCompra(Compra compra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Compra findCompraById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = false)
	public Compra updateCompra(Compra compra, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = false)
	public void deleteMaquina(Long id) {
		// TODO Auto-generated method stub
		
	}

}
