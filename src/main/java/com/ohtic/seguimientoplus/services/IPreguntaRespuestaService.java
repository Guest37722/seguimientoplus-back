package com.ohtic.seguimientoplus.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ohtic.seguimientoplus.entity.Pregunta_Respuesta;

public interface IPreguntaRespuestaService {

	public List<Pregunta_Respuesta> findAll();
		
	public Pregunta_Respuesta save(Pregunta_Respuesta preguntaRespuesta);
	
	public Pregunta_Respuesta getById(int id);
	
	public void deleteById(int id);

	public Page<Pregunta_Respuesta> findAllByPacienteId(int idPaciente, Pageable pageable);

	public void deleteByPacienteId(int id);
}
