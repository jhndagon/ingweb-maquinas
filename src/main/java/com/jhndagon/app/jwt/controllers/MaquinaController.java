package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.services.IMaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@Secured({"ROLE_ADMIN"})
@RequestMapping(value = "/api/maquinas")
public class MaquinaController {

    @Autowired
    private IMaquinaService maquinaService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Maquina crearMaquina(@RequestBody Maquina maquina) {
        return maquinaService.createMaquina(maquina);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Maquina maquina(@PathVariable Long id) {
        return maquinaService.findById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<Maquina> maquinas(
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(value = 30) int size) {
        Page<Maquina> maquinas = maquinaService.findAllMaquinas(page, size);
        return maquinas;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Maquina updateMaquina(@RequestBody Maquina maquina,@PathVariable Long id){
        return maquinaService.updateMaquina(maquina,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMaquina(@PathVariable Long id) {
        maquinaService.deleteMaquina(id);
    }


}
