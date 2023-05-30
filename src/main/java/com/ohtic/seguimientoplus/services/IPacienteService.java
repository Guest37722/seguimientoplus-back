package com.ohtic.seguimientoplus.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ohtic.seguimientoplus.entity.Paciente;

public interface IPacienteService {

	public List<Paciente> findAll();
	
	public Page<Paciente> findAll(Pageable pageable);
	
	public Paciente save(Paciente paciente);
	
	public Paciente getById(int id);
	
	public void deleteById(int id);
}
