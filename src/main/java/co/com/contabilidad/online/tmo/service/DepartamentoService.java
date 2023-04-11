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
import co.com.contabilidad.online.tmo.facade.DepartamentoFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Departamento/")
public class DepartamentoService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(GenericoService.class);

	@Autowired
	private DepartamentoFacade departamentoFacade;
	
	@RequestMapping(value = "obtenerDepartamentos", method = RequestMethod.GET)
	public ResponseEntity<List<DepartamentoDTO>> obtenerDepartamentos(
			@PathParam("codigoDep") Integer codigoDep) {
		lOGGER.info("Ingreso obtenerDepartamentos:  ");
		lOGGER.info("---------------CODIGO DEPARTAMENTO------------:  " + codigoDep);
		List<DepartamentoDTO> lstDepartamentos = new ArrayList<DepartamentoDTO>();
		try {
            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setCodigo(codigoDep);
            lstDepartamentos = departamentoFacade.getDepartamento().consultarDepartamentos(departamento);
			lOGGER.info("---------------CANTIDAD DE DEPARTAMENTOS------------:  " + lstDepartamentos.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerDepartamentos: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerDepartamentos  ");
		return new ResponseEntity<List<DepartamentoDTO>>(lstDepartamentos, HttpStatus.OK);
	}

}
