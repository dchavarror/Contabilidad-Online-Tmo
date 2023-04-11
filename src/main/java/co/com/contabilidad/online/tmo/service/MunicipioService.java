package co.com.contabilidad.online.tmo.service;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.contabilidad.online.tmo.dto.DepartamentoDTO;
import co.com.contabilidad.online.tmo.dto.MunicipioDTO;
import co.com.contabilidad.online.tmo.facade.MunicipioFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Municipio/")
public class MunicipioService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(MunicipioService.class);

	@Autowired
	private MunicipioFacade municipioFacade;
	
	@RequestMapping(value = "obtenerMunicipios", method = RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> obtenerMunicipios(
			@PathParam("codigoDep") Integer codigoDep) {
		lOGGER.info("Ingreso obtenerMunicipios:  ");
		lOGGER.info("---------------CODIGO DEPARTAMENTO------------:  " + codigoDep);
		List<MunicipioDTO> lstMunicipios = new ArrayList<MunicipioDTO>();
		try {
            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setCodigo(codigoDep);
            lstMunicipios = municipioFacade.getMunicipio().consultarMunicipios(departamento);
			lOGGER.info("---------------CANTIDAD DE MUNIPIOS------------:  " + lstMunicipios.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerMunicipios: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerMunicipios  ");
		return new ResponseEntity<List<MunicipioDTO>>(lstMunicipios, HttpStatus.OK);
	}

}
