package com.ohtic.seguimientoplus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ohtic.seguimientoplus.entity.Documento;

public interface IDocumentoService {

	
	public Page<Documento> findAllByPacienteId(int idPaciente, Pageable pageable);
	
	public Documento save(Documento documento);
	
	public Documento getById(int id);
	
	public void deleteById(int id);

	public void deleteByPacienteId(int id);
}
