package com.ohtic.seguimientoplus.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ohtic.seguimientoplus.entity.Pregunta;

public interface IPreguntaService {

	public List<Pregunta> findAll();
	
	public Page<Pregunta> findAll(Pageable pageable);
	
	public Pregunta save(Pregunta pregunta);
	
	public Pregunta getById(int id);
	
	public void deleteById(int id);
}
