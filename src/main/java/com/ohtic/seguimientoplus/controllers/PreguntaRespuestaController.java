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

import com.ohtic.seguimientoplus.entity.Pregunta_Respuesta;
import com.ohtic.seguimientoplus.services.IPreguntaRespuestaService;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pregunta_respuesta")
public class PreguntaRespuestaController {

	@Autowired
	private IPreguntaRespuestaService preguntaRespuestaService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Pregunta_Respuesta> preguntas = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntas = preguntaRespuestaService.findAll();
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (preguntas == null) {
			respuesta.put("error", "No se encuentran preguntas.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Pregunta_Respuesta>>(preguntas,HttpStatus.OK);
		}
	}
	
	@GetMapping("/all/{idPaciente}/{page}")
	public ResponseEntity<?> getAllByPacienteId(@PathVariable int idPaciente, @PathVariable int page){
		Page<Pregunta_Respuesta> preguntas = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntas = preguntaRespuestaService.findAllByPacienteId(idPaciente,PageRequest.of(page, 15));
		} catch (Exception e) {	
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (preguntas == null) {
			respuesta.put("error", "No se encuentran preguntas.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Pregunta_Respuesta>>(preguntas,HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Pregunta_Respuesta pregunta = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			pregunta = preguntaRespuestaService.getById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Pregunta no encontrada.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta_Respuesta>(pregunta,HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Pregunta_Respuesta pregunta){
		Pregunta_Respuesta preguntaCreada = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntaCreada = preguntaRespuestaService.save(pregunta);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Error al crear la pregunta.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta_Respuesta>(preguntaCreada,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntaRespuestaService.deleteById(id);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Pregunta borrada con éxito.");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Pregunta_Respuesta pregunta){
		Pregunta_Respuesta preguntaActualzida = null;
		Map<String,Object> respuesta = new HashMap<>();
		try {
			preguntaActualzida = preguntaRespuestaService.save(pregunta);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pregunta == null) {
			respuesta.put("error", "Error al actualizar la pregunta.");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pregunta_Respuesta>(preguntaActualzida,HttpStatus.OK);
		}
	}
}
