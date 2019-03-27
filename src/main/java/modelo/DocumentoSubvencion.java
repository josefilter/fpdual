package modelo;

import java.util.Date;

public class DocumentoSubvencion extends Documento {

	private final int numeroSubvenciones;

	
	public DocumentoSubvencion(int id, String nombre, Usuario usuario, Date fechaCreacion, int numeroSubvenciones) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_SUBVENCION);
		this.numeroSubvenciones = numeroSubvenciones;
	}


	public int getNumeroSubvenciones() {
		return numeroSubvenciones;
	}
	
}
