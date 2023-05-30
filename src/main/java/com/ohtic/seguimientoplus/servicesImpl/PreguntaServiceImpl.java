package com.ohtic.seguimientoplus.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ohtic.seguimientoplus.entity.Pregunta;
import com.ohtic.seguimientoplus.repository.IPreguntaRepository;
import com.ohtic.seguimientoplus.services.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService{
	
	@Autowired
	private IPreguntaRepository preguntaRepository;

	@Override
	public List<Pregunta> findAll() {
		return preguntaRepository.findAll();
	}

	@Override
	public Pregunta save(Pregunta pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public Pregunta getById(int id) {
		return preguntaRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		preguntaRepository.deleteById(id);
		
	}

	@Override
	public Page<Pregunta> findAll(Pageable pageable) {
		return preguntaRepository.findAll(pageable);
	}
	
	
}
