package com.ohtic.seguimientoplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ohtic.seguimientoplus.entity.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Integer> {
	

}
