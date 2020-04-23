package com.jhndagon.app.jwt.services;

import java.util.List;

import com.jhndagon.app.jwt.models.Contrato;

public interface IContratoService {
	
	List<Contrato> getContratos();
	
	Contrato getContrato(Integer id);

}
