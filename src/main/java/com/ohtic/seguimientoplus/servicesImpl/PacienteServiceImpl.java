package com.ohtic.seguimientoplus.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ohtic.seguimientoplus.entity.Paciente;
import com.ohtic.seguimientoplus.repository.IPacienteRepository;
import com.ohtic.seguimientoplus.services.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService{
	
	@Autowired
	private IPacienteRepository pacienteRepository;

	@Override
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}

	@Override
	public Paciente save(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente getById(int id) {
		return pacienteRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		pacienteRepository.deleteById(id);
		
	}

	@Override
	public Page<Paciente> findAll(Pageable pageable) {
		return pacienteRepository.findAll(pageable);
	}
	
	
}
