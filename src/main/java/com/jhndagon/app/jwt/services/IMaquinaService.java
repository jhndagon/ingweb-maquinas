package com.jhndagon.app.jwt.services;

import com.jhndagon.app.jwt.models.Maquina;


public interface IMaquinaService {

    Maquina createMaquina(Maquina maquina);

    Maquina findById(Long id);

    Maquina updateMaquina(Maquina maquina, Long id);

    void deleteMaquina(Long id);
}
