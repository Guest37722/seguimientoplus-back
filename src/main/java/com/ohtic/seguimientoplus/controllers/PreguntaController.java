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

import com.ohtic.seguimientoplus.entity.Pregunta;
import com.ohtic.seguimientoplus.services.IPreguntaService;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pregunta")
public class PreguntaController {

	@Autowired
	private IPreguntaService preguntaService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Pregunta> preguntas = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntas = preguntaService.findAll();
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (preguntas == null) {
			respuesta.put("error", "No se encuentran preguntas.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Pregunta>>(preguntas,HttpStatus.OK);
		}
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<?> getAll(@PathVariable int page){
		Page<Pregunta> preguntas = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntas = preguntaService.findAll(PageRequest.of(page, 15));
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (preguntas == null) {
			respuesta.put("error", "No se encuentran preguntas.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Pregunta>>(preguntas,HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Pregunta pregunta = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pregunta = preguntaService.getById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Pregunta no encontrada.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta>(pregunta,HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Pregunta pregunta){
		Pregunta preguntaCreada = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntaCreada = preguntaService.save(pregunta);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Error al crear la pregunta.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta>(preguntaCreada,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		Map<String,Object> respuesta = new HashMap<>();
		try {
			//preguntaRespuestaService.deleteByPacienteId(id);
			//seguimientoService.deleteByPacienteId(id);
			preguntaService.deleteById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Pregunta borrada con éxito.");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Pregunta pregunta){
		Pregunta preguntaActualzida = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntaActualzida = preguntaService.save(pregunta);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Error al actualizar la pregunta.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta>(preguntaActualzida,HttpStatus.OK);
		}
	}
}
