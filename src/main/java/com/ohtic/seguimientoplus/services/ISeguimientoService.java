package com.ohtic.seguimientoplus.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ohtic.seguimientoplus.entity.Seguimiento;

public interface ISeguimientoService {

	public List<Seguimiento> findAllByPacienteId(int idPaciente);
	
	public Page<Seguimiento> findAllByPacienteId(int idPaciente, Pageable pageable);
	
	public Seguimiento save(Seguimiento seguimiento);
	
	public Seguimiento getById(int id);
	
	public void deleteById(int id);

	public void deleteByPacienteId(int id);
}
