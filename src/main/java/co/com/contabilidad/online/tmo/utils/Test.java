package co.com.contabilidad.online.tmo.utils;

import java.util.ArrayList;
import java.util.List;

import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;

public class Test {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		int n=3;
		String valorx = "x";
		String valorEspacio = "_";
		String resultado = "";
		boolean aplicaX = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (aplicaX) {
					resultado += valorx;
					aplicaX = false;
				} else {
					resultado += valorEspacio;
					aplicaX = true;
				}
			}
			resultado = resultado + "\n";
		}
		System.out.println("\n" + resultado);
	}
}
