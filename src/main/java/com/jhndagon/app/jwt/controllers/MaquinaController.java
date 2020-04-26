package com.jhndagon.app.jwt.controllers;

import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.models.PuntoDeVenta;
import com.jhndagon.app.jwt.services.IMaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/maquinas")
public class MaquinaController {

    @Autowired
    private IMaquinaService maquinaService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<?> crearMaquina(@RequestBody Maquina maquina) {
        Maquina maquinaNew=null;
        Map<String, Object> response = new HashMap<>();

        try{
            maquinaNew= maquinaService.createMaquina(maquina);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La maquina ha sido creada");
        response.put("Maquina",maquinaNew);

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }

    @Secured({"ROLE_ADMIN_PUNTO", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> maquina(@PathVariable Long id) {
        Maquina maquina=null;
        Map<String, Object> response = new HashMap<>();
        try {
            maquina = maquinaService.findById(id);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(maquina==null){
            response.put("mensaje", "La maquina con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Maquina>(maquina, HttpStatus.OK);

    }

    @Secured({"ROLE_ADMIN_PUNTO", "ROLE_ADMIN"})
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<Maquina> maquinas(
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(value = 30) int size) {
        Page<Maquina> maquinas = maquinaService.findAllMaquinas(page, size);
        return maquinas;
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateMaquina(@RequestBody Maquina maquina,@PathVariable Long id){

        Maquina maquinaActual= maquinaService.findById(id);
        Maquina maquinaUp =null;
        Map<String, Object> response = new HashMap<>();

        if(maquinaActual==null){
            response.put("mensaje", "La maquina con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            maquinaUp=maquinaService.updateMaquina(maquina,id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","La maquina ha sido actualizada");
        response.put("Maquina",maquinaUp);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarMaquina(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            maquinaService.deleteMaquina(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La maquina ha sido eliminada");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
