package es.fpdual.primero.eadmin.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.fpdual.primero.eadmin.modelo.AdministracionElectronicaException;
import es.fpdual.primero.eadmin.modelo.Documento;

@Repository
public class RepositorioDocumentoEnLista implements RepositorioDocumento {

	private final List<Documento> documentos = new ArrayList<>();
	

	@Override
	public void altaDocumento(Documento documento) {
		
		if(documentos.contains(documento)) {
			throw new AdministracionElectronicaException("El documento ya existe.");
		}
		
		documentos.add(documento);
	}

	@Override
	public void modificarDocumento(Documento documento) {
		
		if(!documentos.contains(documento)) {
			throw new AdministracionElectronicaException("El documento no existe.");
		}
		
		documentos.set(documentos.indexOf(documento), documento);
	}

	@Override
	public void eliminarDocumento(int codigoDocumento) {
		
		if(!documentos.contains(documentos.get(codigoDocumento))) {
			throw new AdministracionElectronicaException("El documento no existe.");
		}
		
		//solucion 1
		final Documento documentoAEliminar = new Documento(codigoDocumento, null, null, null, null);
		
		final int indice = documentos.indexOf(documentoAEliminar);
		
		documentos.remove(indice);
		
		//solucion 2
//		documentoAEliminar = documentos.stream().filter(d -> d.getId().intValue==codigoDocumento).findAny().orElse(null);
		
		//documentos.remove(documentos.indexOf(documentos.get(codigoDocumento)));
		
	}

	@Override
	public List<Documento> obtenerTodosLosDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSiguienteID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
