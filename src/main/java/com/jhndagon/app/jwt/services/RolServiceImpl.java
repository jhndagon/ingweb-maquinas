package com.jhndagon.app.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhndagon.app.jwt.models.Rol;
import com.jhndagon.app.jwt.repositories.IRol;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRol rolRespository;

	@Override
	public List<Rol> getRoles() {
		return (List<Rol>)rolRespository.findAll();
	}

	@Override
	public Rol getRolById(Integer rolId) {
		return rolRespository.findById(rolId).orElse(null);
	}
	
	
}
