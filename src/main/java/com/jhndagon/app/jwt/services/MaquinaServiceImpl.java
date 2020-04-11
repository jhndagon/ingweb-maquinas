package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.repositories.IMaquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service

public class MaquinaServiceImpl implements IMaquinaService {


    @Autowired
    private IMaquina maquinaRepository;

    @Override
    @Transactional(readOnly = false)
    public Maquina createMaquina(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    @Override
    @Transactional(readOnly = true)
    public Maquina findById(Long id) {
       return maquinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Maquina no registrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Maquina> findAllMaquinas(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return maquinaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public Maquina updateMaquina(Maquina maquina, Long id) {
        Maquina maquinaActual= findById(id);
        maquinaActual.setMarca(maquina.getMarca());
        maquinaActual.setModelo(maquina.getModelo());
        maquinaActual.setTipo(maquina.getTipo());
        maquinaActual.setFechaCreacion(maquina.getFechaCreacion());

        return maquinaRepository.save(maquinaActual);

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMaquina(Long id) {
        maquinaRepository.deleteById(id);
    }

}
