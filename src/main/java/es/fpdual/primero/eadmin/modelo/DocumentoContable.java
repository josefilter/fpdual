package es.fpdual.primero.eadmin.modelo;

import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import es.fpdual.primero.eadmin.EadminApplication;

public class DocumentoContable extends Documento {

	private final String numeroOperacionContable;
	private static final Logger logger = LogManager.getLogger(EadminApplication.class);

	public DocumentoContable(int id, String nombre, Usuario usuario, Date fechaCreacion, String numeroOperacionContable) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_CONTABLE);
		this.numeroOperacionContable = numeroOperacionContable;
		logger.trace("Se ha creado un documento contable");
	}

	public String getNumeroOperacionContable() {
		return numeroOperacionContable;
	}	
}
