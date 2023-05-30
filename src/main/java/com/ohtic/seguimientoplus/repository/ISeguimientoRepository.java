package com.ohtic.seguimientoplus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ohtic.seguimientoplus.entity.Seguimiento;

public interface ISeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
	
	public List<Seguimiento> findAllByPacienteId(@Param("pacienteId") int pacienteId);
	
	public Page<Seguimiento> findAllByPacienteId(@Param("pacienteId") int pacienteId, Pageable pageable);

	public void deleteByPacienteId(@Param("pacienteId") int pacienteId);
	

}
