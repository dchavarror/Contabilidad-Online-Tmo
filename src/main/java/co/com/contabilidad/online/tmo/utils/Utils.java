package co.com.contabilidad.online.tmo.utils;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;

public class Utils {

	public static String obtenerTelefonos(final List<TelefonoDTO> lstTelefonos) {
		String telefonos ="TELS: ";
		
		for(int i=0;i<lstTelefonos.size();i++) {
			if(lstTelefonos.get(i).getNumero() !="") {
				if(i == lstTelefonos.size()-1) {
					telefonos += lstTelefonos.get(i).getNumero();
				}else {
					telefonos += lstTelefonos.get(i).getNumero() + "-";
				}
				
			}
		}
		return telefonos;
		
	}
	
	public static Double obtenerSubTotal(final List<VentaDTO> lstVentas) {
		Double subTotal = 0.0;
		for (int i = 0; i < lstVentas.size(); i++) {
			subTotal += lstVentas.get(i).getValorTotal();
		}
		
		return subTotal;
	}
}
