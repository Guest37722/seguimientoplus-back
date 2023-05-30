package com.ohtic.seguimientoplus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ohtic.seguimientoplus.entity.Pregunta_Respuesta;

public interface IPreguntaRespuestaRepository extends JpaRepository<Pregunta_Respuesta, Integer> {

	Page<Pregunta_Respuesta> findAllByIdPaciente(int idPaciente, Pageable pageable);

	void deleteByIdPaciente(@Param("idPaciente") int idPaciente);
	

}
