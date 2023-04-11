package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.ISegmento;

@Component
public class SegmentoFacade {
	
	@Autowired
	private ISegmento segmento;

	public ISegmento getSegmento() {
		return segmento;
	}

}
