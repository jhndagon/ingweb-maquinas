package com.jhndagon.app.jwt.utils;

import java.util.ArrayList;
import java.util.List;

import com.jhndagon.app.jwt.models.Compra;
import com.jhndagon.app.jwt.models.Maquina;
import com.jhndagon.app.jwt.models.Venta;

public class Calculos {
	
	public static List<Maquina> getMaquinaCantidad(List<Compra> compras, List<Venta> ventas){
		List<Maquina> maquinas = new ArrayList();
		
		for (Compra compra : compras) {
			if(maquinas.contains(compra.getMaquina())) {
				int index = maquinas.indexOf(compra.getMaquina());
				Maquina maquina = maquinas.get(index);
				int suma = maquina.getCantidad() + compra.getCantidad();
				maquina.setCantidad(suma);
			}
			else {
				Maquina maquina = compra.getMaquina();
				maquina.setCantidad(compra.getCantidad());
				maquinas.add(compra.getMaquina());
			}
		}
		
		for (Venta venta : ventas) {
			if(maquinas.contains(venta.getMaquina())) {
				int index = maquinas.indexOf(venta.getMaquina());
				Maquina maquina = maquinas.get(index);
				int resta = maquina.getCantidad() - venta.getCantidad();
				maquina.setCantidad(resta);
			}			
			else {
				Maquina maquina = venta.getMaquina();
				maquina.setCantidad(-venta.getCantidad());
				maquinas.add(venta.getMaquina());
			}
		}		
		return maquinas;
	}

}
