package com.ohtic.seguimientoplus.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ohtic.seguimientoplus.entity.Seguimiento;
import com.ohtic.seguimientoplus.services.ISeguimientoService;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seguimiento")
public class SeguimientoController {

	@Autowired
	private ISeguimientoService seguimientoService;
	
	@GetMapping("/all/{idPaciente}")
	public ResponseEntity<?> getAllByPacienteId(@PathVariable int idPaciente){
		List<Seguimiento> seguimientos = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimientos = seguimientoService.findAllByPacienteId(idPaciente);
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (seguimientos == null) {
			respuesta.put("error", "No se encuentran seguimientos.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Seguimiento>>(seguimientos,HttpStatus.OK);
		}
	}
	
	@GetMapping("/all/{idPaciente}/{page}")
	public ResponseEntity<?> getAllByPacienteId(@PathVariable int idPaciente, @PathVariable int page){
		Page<Seguimiento> seguimientos = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimientos = seguimientoService.findAllByPacienteId(idPaciente, PageRequest.of(page, 5));
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (seguimientos == null) {
			respuesta.put("error", "No se encuentran seguimientos.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Seguimiento>>(seguimientos,HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Seguimiento seguimiento = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimiento = seguimientoService.getById(id);
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (seguimiento == null) {
			respuesta.put("error", "Seguimiento no encontrado.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Seguimiento>(seguimiento,HttpStatus.OK);
		}
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Seguimiento seguimiento){
		Seguimiento seguimientoCreado = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimientoCreado = seguimientoService.save(seguimiento);
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (seguimiento == null) {
			respuesta.put("error", "Error al crear el seguimiento.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Seguimiento>(seguimientoCreado,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimientoService.deleteById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Seguimiento borrado con éxito.");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Seguimiento seguimiento){
		Seguimiento seguimientoCreado = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			seguimientoCreado = seguimientoService.save(seguimiento);
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (seguimiento == null) {
			respuesta.put("error", "Error al actualizar el seguimiento.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Seguimiento>(seguimientoCreado,HttpStatus.OK);
		}
	}
}
