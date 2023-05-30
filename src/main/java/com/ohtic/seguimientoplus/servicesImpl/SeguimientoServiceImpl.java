package com.ohtic.seguimientoplus.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ohtic.seguimientoplus.entity.Seguimiento;
import com.ohtic.seguimientoplus.repository.ISeguimientoRepository;
import com.ohtic.seguimientoplus.services.ISeguimientoService;

@Service
public class SeguimientoServiceImpl implements ISeguimientoService{
	
	@Autowired
	private ISeguimientoRepository seguimientoRepository;

	@Override
	public List<Seguimiento> findAllByPacienteId(int idPaciente) {
		return seguimientoRepository.findAllByPacienteId(idPaciente);
	}

	@Override
	public Seguimiento save(Seguimiento seguimiento) {
		return seguimientoRepository.save(seguimiento);
	}

	@Override
	public Seguimiento getById(int id) {
		return seguimientoRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		seguimientoRepository.deleteById(id);
		
	}

	@Override
	public Page<Seguimiento> findAllByPacienteId(int idPaciente, Pageable pageable) {
		return seguimientoRepository.findAllByPacienteId(idPaciente,pageable);
	}

	@Override
	public void deleteByPacienteId(int id) {
		seguimientoRepository.deleteByPacienteId(id);
		
	}
	
	
}
