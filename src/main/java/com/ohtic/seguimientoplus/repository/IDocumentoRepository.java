package com.ohtic.seguimientoplus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ohtic.seguimientoplus.entity.Documento;

public interface IDocumentoRepository extends JpaRepository<Documento, Integer> {
	
	
	public Page<Documento> findAllByIdPaciente(@Param("idPaciente") int idPaciente, Pageable pageable);

	public void deleteByIdPaciente(@Param("idPaciente") int idPaciente);
	

}
