package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.Maquina;
import org.springframework.data.domain.Page;


public interface IMaquinaService {

    Maquina createMaquina(Maquina maquina);

    Maquina findById(Long id);

    Page<Maquina> findAllMaquinas(int page, int size);

    Maquina updateMaquina(Maquina maquina, Long id);

    void deleteMaquina(Long id);
}
