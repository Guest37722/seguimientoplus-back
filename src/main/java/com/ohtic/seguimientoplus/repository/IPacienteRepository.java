package com.ohtic.seguimientoplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ohtic.seguimientoplus.entity.Paciente;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
	

}
