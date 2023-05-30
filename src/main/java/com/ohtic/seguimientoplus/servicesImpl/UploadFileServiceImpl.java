package com.ohtic.seguimientoplus.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ohtic.seguimientoplus.entity.Documento;
import com.ohtic.seguimientoplus.services.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final static String UPLOADS_FOLDER = "documentosPacientes";

	@Override
	public void subirDocumento(MultipartFile file, Documento doc) throws IOException {

		Path rootPath = getPath(doc);
		comprobarDirectorio(doc.getIdPaciente());
		if (Files.notExists(rootPath)) {
			Files.copy(file.getInputStream(), rootPath);
		}

	}

	@Override
	public boolean delete(Documento doc) {
		Path rootPath = getPath(doc);
		File archivo = rootPath.toFile();
		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(Documento doc) {
		return Paths.get(UPLOADS_FOLDER + File.separator + doc.getIdPaciente() + File.separator).resolve(doc.getNombre())
				.toAbsolutePath();
	}

	@Override
	public void comprobarDirectorio(Integer idPaciente) throws IOException {
		if (!Files.exists(Paths.get(UPLOADS_FOLDER))) {
			Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		}
		if (!Files.exists(Paths.get(UPLOADS_FOLDER + File.separator + idPaciente))) {
			Files.createDirectory(Paths.get(UPLOADS_FOLDER + File.separator + idPaciente));
		}
	}

	@Override
	public Resource devolverDocumento(Documento doc) throws IOException {
		String ruta = UPLOADS_FOLDER + File.separator + doc.getIdPaciente() + File.separator + doc.getNombre();
		File file = new File(ruta);
		if (file.exists()) {
			Resource resource = new UrlResource(file.toURI());
			if (resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo!");
			}
		}
		return null;
	}

}
