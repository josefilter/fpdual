package es.fpdual.primero.eadmin.modelo;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import es.fpdual.primero.eadmin.EadminApplication;

public class DocumentoFactura extends Documento{

	private final double importeFactura;
	private static final Logger logger = LogManager.getLogger(EadminApplication.class);
	
	public DocumentoFactura(int id, String nombre, Usuario usuario, Date fechaCreacion, double importeFactura) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_FACTURA);
		this.importeFactura = importeFactura;
		logger.warn("Se ha creado un documento factura");
	}


	public double getImporteFactura() {
		return importeFactura;
	}
}
