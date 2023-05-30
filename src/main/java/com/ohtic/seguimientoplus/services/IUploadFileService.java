package com.ohtic.seguimientoplus.services;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ohtic.seguimientoplus.entity.Documento;



public interface IUploadFileService {

	void subirDocumento(MultipartFile file, Documento doc) throws IOException;

	void comprobarDirectorio(Integer idDocumento) throws IOException;

	Resource devolverDocumento(Documento doc) throws IOException;

	boolean delete(Documento doc);
	
	
	
}
