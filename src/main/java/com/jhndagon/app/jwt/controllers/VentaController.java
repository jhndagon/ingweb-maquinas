package com.jhndagon.app.jwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.services.ICompraService;
import com.jhndagon.app.jwt.utils.Calculos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhndagon.app.jwt.models.Venta;
import com.jhndagon.app.jwt.services.IVentaService;

@RestController
@RequestMapping(value = "/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private ICompraService compraService;
    
//    @Secured({"ROLE_ADMIN_PUNTO"})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crearventa(@RequestBody Venta venta) {
        Venta ventaNew=null;
        Map<String, Object> response = new HashMap<>();

        try{
            Long puntoVentaId = venta.getEmpleado().getPuntoVenta().getId();
            Long maquinaId = venta.getMaquina().getId();
            List<Compra> compras = compraService.findByPuntoVentaMaquina(puntoVentaId,maquinaId);
            List<Venta> ventas = ventaService.findByPuntoVentaMaquina(puntoVentaId,maquinaId);
            List<Maquina> maquinas = Calculos.getMaquinaCantidad(compras, ventas);

            if(!maquinas.isEmpty()){
                if(maquinas.get(0).getCantidad()>=venta.getCantidad()){
                    ventaNew= ventaService.createVenta(venta);
                }else{
                    response.put("mensaje", "La cantidad de maquinas con esas caracteristicas no se encuentra disponible");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }

            }else{
                response.put("mensaje", "La cantidad de maquinas con esas caracteristicas no se encuentra disponible");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje","La venta ha sido creada");
        response.put("Venta",ventaNew);

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }
    
//    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_PUNTO"})
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<Venta> ventas(
			@RequestParam(name="page" ,defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") @Max(value = 100) int size) {
		Page<Venta> ventas = ventaService.findAllVenta(page, size);
		return ventas;
	}

//    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_PUNTO"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> venta(@PathVariable Long id) {
        Venta venta=null;
        Map<String, Object> response = new HashMap<>();
        try {
            venta = ventaService.findVentaById(id);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(venta==null){
            response.put("mensaje", "La venta con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Venta>(venta, HttpStatus.OK);
    }

//    @Secured({"ROLE_ADMIN_PUNTO"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateVenta(@RequestBody Venta venta,@PathVariable Long id){

        Venta ventaActual= ventaService.findVentaById(id);
        Venta ventaNew =null;
        Map<String, Object> response = new HashMap<>();

        if(ventaActual==null){
            response.put("mensaje", "La venta con id: ".concat(id.toString().concat(", no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            ventaNew=ventaService.updateVenta(venta,id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","La venta ha sido actualizada");
        response.put("Venta",ventaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

}

//    @Secured({"ROLE_ADMIN_PUNTO"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            ventaService.deleteVenta(id);
        }
        catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La venta ha sido eliminada");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
     }
	
}
