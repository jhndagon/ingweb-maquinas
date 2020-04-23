package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhndagon.app.jwt.models.Contrato;
import com.jhndagon.app.jwt.repositories.IContrato;

@Service
public class ContratoServiceImpl implements IContratoService{
	
	@Autowired
	private IContrato contratoRepository;

	@Override
	public List<Contrato> getContratos() {
		return (List<Contrato>)contratoRepository.findAll();
	}

	@Override
	public Contrato getContrato(Integer id) {
		return contratoRepository.findById(id).orElse(null);
	}

}
