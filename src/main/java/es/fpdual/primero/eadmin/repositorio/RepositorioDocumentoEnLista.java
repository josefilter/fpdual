package es.fpdual.primero.eadmin.repositorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.fpdual.primero.eadmin.EadminApplication;
import es.fpdual.primero.eadmin.modelo.AdministracionElectronicaException;
import es.fpdual.primero.eadmin.modelo.Documento;

@Repository
public class RepositorioDocumentoEnLista implements RepositorioDocumento {

	private final List<Documento> documentos = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(EadminApplication.class);

	@Override
	public void altaDocumento(Documento documento) {
		
		documentos.add(documento);
		try {
			String nombre = (documento.getNombre() + "-" + documento.getTipoDocumento() + ".txt");
			File fichero2 = new File(nombre);
			
			if (fichero2.exists()) {
				logger.error("El fichero YA existe");
			} else {
				logger.error("El fichero no existe");
				FileWriter fichero = new FileWriter(documento.getNombre() + "-" + documento.getTipoDocumento() + ".txt");
				PrintWriter impFichero = new PrintWriter(fichero);
				
				impFichero.println("ID: " + documento.getId());
				impFichero.println("Nombre: " + documento.getNombre());
				impFichero.println("Usuario: " + documento.getUsuario());
				impFichero.println("Fecha Creacion: " + documento.getFechaCreacion());
				impFichero.println("Tipo Documento: " + documento.getTipoDocumento());

				impFichero.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.trace("\n**********************************************");
		logger.trace("\nDocumeto creado correctamente");
		logger.trace("\nID: " + documento.getId());
		logger.trace("\nNOMBRE: " + documento.getNombre());
		logger.trace("\nUSUARIO: " + documento.getUsuario());
		logger.trace("\nFECHA CREACION: " + documento.getFechaCreacion());
		logger.trace("\nTIPO DOCUMENTO: " + documento.getTipoDocumento());
		logger.trace("\n**********************************************");
	}

	@Override
	public void modificarDocumento(Documento documento) {

		if (!documentos.contains(documento)) {
			// throw new AdministracionElectronicaException("El documento no existe.");
			logger.warn("El documento no existe", new AdministracionElectronicaException("El documento no existe"));
		}

		documentos.set(documentos.indexOf(documento), documento);
		logger.info(documento.toString());
	}

	@Override
	public void eliminarDocumento(int id) {

		// solucion 1
		Documento documentoAEliminar = new Documento(id, null, null, null, null);
		// solucion 2
		// documentoAEliminar = documentos.stream().filter(d -> d.getId() ==
		// id).findAny().orElse(null);

		final int indice = documentos.indexOf(documentoAEliminar);

		if (indice > -1) {
			documentos.remove(indice);
		}
	}

	@Override
	public List<Documento> obtenerTodosLosDocumento() {
		
		try {
		FileWriter fichero = new FileWriter("ListaDocumento.txt");
		PrintWriter impFichero = new PrintWriter(fichero);
		
		for(Documento documento: documentos) {
			impFichero.println("-------------------------------");
			impFichero.println("ID: " + documento.getId());
			impFichero.println("Nombre: " + documento.getNombre());
			impFichero.println("Usuario: " + documento.getUsuario());
			impFichero.println("Fecha Creacion: " + documento.getFechaCreacion());
			impFichero.println("Tipo Documento: " + documento.getTipoDocumento());
		}
		impFichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.documentos.stream().collect(Collectors.toList());
	}

	@Override
	public int getSiguienteID() {

		if (documentos.isEmpty()) {
			return 1;
		}
		return documentos.get(documentos.size() - 1).getId() + 1;
	}

	@Override
	public Documento obtenerDocumentoPorId(int codigoDocumento) {

		return documentos.stream().filter(d -> d.getId() == codigoDocumento).findFirst().orElse(null);
	}

}
