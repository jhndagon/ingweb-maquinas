package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.services.IMaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class MaquinaController {

    @Autowired
    private IMaquinaService maquinaService;

    @PostMapping("/maquina")
    @ResponseStatus(HttpStatus.CREATED)
    public Maquina crearMaquina(@RequestBody Maquina maquina) {
        return maquinaService.createMaquina(maquina);
    }

    @GetMapping("/maquina/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Maquina Maquina(@PathVariable Long id) {
        return maquinaService.findById(id);
    }

    @PutMapping("/maquina/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Maquina updateMaquina(@RequestBody Maquina maquina,@PathVariable Long id){
        return maquinaService.updateMaquina(maquina,id);
    }

    @DeleteMapping("/maquina/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMaquina(@PathVariable Long id) {
        maquinaService.deleteMaquina(id);
    }


}
