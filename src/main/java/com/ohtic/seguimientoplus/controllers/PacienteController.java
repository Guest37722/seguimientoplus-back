package com.ohtic.seguimientoplus.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohtic.seguimientoplus.entity.Paciente;
import com.ohtic.seguimientoplus.services.IDocumentoService;
import com.ohtic.seguimientoplus.services.IPacienteService;
import com.ohtic.seguimientoplus.services.IPreguntaRespuestaService;
import com.ohtic.seguimientoplus.services.ISeguimientoService;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;
	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private ISeguimientoService seguimientoService;
	@Autowired
	private IPreguntaRespuestaService respuestasService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Paciente> pacientes = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pacientes = pacienteService.findAll();
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pacientes == null) {
			respuesta.put("error", "No se encuentran pacientes.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<List<Paciente>>(pacientes,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<?> getAll(@PathVariable int page){
		Page<Paciente> pacientes = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pacientes = pacienteService.findAll(PageRequest.of(page, 15));
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pacientes == null) {
			respuesta.put("error", "No se encuentran pacientes.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Page<Paciente>>(pacientes,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Paciente paciente = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			paciente = pacienteService.getById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (paciente == null) {
			respuesta.put("error", "No se encuentran el paciente.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Paciente>(paciente,HttpStatus.OK);
		}
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Paciente paciente){
		Paciente pacienteCreado = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pacienteCreado = pacienteService.save(paciente);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pacienteCreado == null) {
			respuesta.put("error", "Error al crear el paciente.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Paciente>(pacienteCreado,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pacienteService.deleteById(id);
			documentoService.deleteByPacienteId(id);
			seguimientoService.deleteByPacienteId(id);
			respuestasService.deleteByPacienteId(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Paciente borrado con éxito.");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Paciente paciente){
		Paciente pacienteActualizado = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pacienteActualizado = pacienteService.save(paciente);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pacienteActualizado == null) {
			respuesta.put("error", "Error al actualziar el paciente.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Paciente>(pacienteActualizado,HttpStatus.OK);
		}
	}
}
