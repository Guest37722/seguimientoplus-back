package com.ohtic.seguimientoplus.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ohtic.seguimientoplus.entity.Documento;
import com.ohtic.seguimientoplus.repository.IDocumentoRepository;
import com.ohtic.seguimientoplus.services.IDocumentoService;

@Service
public class DocumentoServiceImpl implements IDocumentoService{
	
	@Autowired
	private IDocumentoRepository documentoRepository;

	@Override
	public Documento save(Documento documento) {
		return documentoRepository.save(documento);
	}

	@Override
	public Documento getById(int id) {
		return documentoRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		documentoRepository.deleteById(id);
	}

	@Override
	public Page<Documento> findAllByPacienteId(int idPaciente, Pageable pageable) {
		return documentoRepository.findAllByIdPaciente(idPaciente,pageable);
	}

	@Override
	public void deleteByPacienteId(int id) {
		documentoRepository.deleteByIdPaciente(id);
		
	}
	
	
}
