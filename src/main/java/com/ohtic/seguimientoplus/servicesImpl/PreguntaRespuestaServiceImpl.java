package com.ohtic.seguimientoplus.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ohtic.seguimientoplus.entity.Pregunta_Respuesta;
import com.ohtic.seguimientoplus.repository.IPreguntaRespuestaRepository;
import com.ohtic.seguimientoplus.services.IPreguntaRespuestaService;

@Service
public class PreguntaRespuestaServiceImpl implements IPreguntaRespuestaService{
	
	@Autowired
	private IPreguntaRespuestaRepository preguntaRespuestaRepository;

	@Override
	public List<Pregunta_Respuesta> findAll() {
		return preguntaRespuestaRepository.findAll();
	}

	@Override
	public Pregunta_Respuesta save(Pregunta_Respuesta pregunta) {
		return preguntaRespuestaRepository.save(pregunta);
	}

	@Override
	public Pregunta_Respuesta getById(int id) {
		return preguntaRespuestaRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		preguntaRespuestaRepository.deleteById(id);
		
	}

	@Override
	public Page<Pregunta_Respuesta> findAllByPacienteId(int idPaciente, Pageable pageable) {
		return preguntaRespuestaRepository.findAllByIdPaciente(idPaciente,pageable);
	}

	@Override
	public void deleteByPacienteId(int id) {
		preguntaRespuestaRepository.deleteByIdPaciente(id);
		
	}
	
	
}
