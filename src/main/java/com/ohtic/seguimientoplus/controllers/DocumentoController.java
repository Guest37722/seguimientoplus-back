package com.ohtic.seguimientoplus.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ohtic.seguimientoplus.entity.Documento;
import com.ohtic.seguimientoplus.services.IDocumentoService;
import com.ohtic.seguimientoplus.services.IUploadFileService;

@CrossOrigin // (origins = "http://localhost:4200")
@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/all/{idPaciente}/{page}")
	public ResponseEntity<?> getAllByPacienteId(@PathVariable int idPaciente, @PathVariable int page) {
		Page<Documento> documentos = null;
		Map<String, Object> respuesta = new HashMap<>();
		try {
			documentos = documentoService.findAllByPacienteId(idPaciente, PageRequest.of(page, 5));
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (documentos == null) {
			respuesta.put("error", "No se encuentran documentos.");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Documento>>(documentos, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/adjuntarDoc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> adjuntarDocumento(@RequestParam("paciente") int idPaciente,
			@RequestParam("file") MultipartFile file) throws IOException {

		try {
			Documento documentoAGuardar = new Documento();
			documentoAGuardar.setIdPaciente(idPaciente);
			documentoAGuardar.setNombre(file.getOriginalFilename());
			uploadFileService.subirDocumento(file, documentoAGuardar);
			documentoService.save(documentoAGuardar);
			
		} catch (IOException e) {
			e.printStackTrace();
			Map<String, Object> response = new HashMap<>();
			response.put("error", "Error al subir el documento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/descargarDocumento")
	public ResponseEntity<?> download(@RequestBody Documento documento) {
		try {
			Resource file = uploadFileService.devolverDocumento(documento);
			Path path = file.getFile().toPath();

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);

		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("Error", "Error al descargar el documento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/eliminarDocumento/{id}")
	public ResponseEntity<?> delDocumento(@PathVariable Integer id) {
		try {
			Documento doc = new Documento();
			doc = documentoService.getById(id);
			if (uploadFileService.delete(doc)) {
				documentoService.deleteById(id);
			}
		} catch (DataAccessException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", "Error al eliminar el documento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
