package es.fpdual.primero.eadmin.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.fpdual.primero.eadmin.modelo.AdministracionElectronicaException;
import es.fpdual.primero.eadmin.modelo.Documento;

@Repository
public class RepositorioDocumentoEnLista implements RepositorioDocumento {

	private final List<Documento> documentos = new ArrayList<>();

	@Override
	public void altaDocumento(Documento documento) {

		if (documentos.contains(documento)) {
			throw new AdministracionElectronicaException("El documento ya existe.");
		}

		documentos.add(documento);
	}

	@Override
	public void modificarDocumento(Documento documento) {

		if (!documentos.contains(documento)) {
			throw new AdministracionElectronicaException("El documento no existe.");
		}

		documentos.set(documentos.indexOf(documento), documento);
	}

	@Override
	public void eliminarDocumento(int id) {

		// solucion 1
		Documento documentoAEliminar = new Documento(id, null, null, null, null);
		// solucion 2
		documentoAEliminar = documentos.stream().filter(d -> d.getId() == id).findAny().orElse(null);

		final int indice = documentos.indexOf(documentoAEliminar);

		documentos.remove(indice);

	}

	@Override
	public List<Documento> obtenerTodosLosDocumento() {

		return this.documentos.stream().collect(Collectors.toList());
	}

	@Override
	public int getSiguienteID() {

		if (documentos.isEmpty()) {
			return 1;
		}
		return documentos.get(documentos.size() - 1).getId() + 1;
	}

}
