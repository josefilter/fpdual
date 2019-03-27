package modelo;

import java.util.Date;

public class DocumentoFactura extends Documento{

	private final double importeFactura;

	
	public DocumentoFactura(int id, String nombre, Usuario usuario, Date fechaCreacion, double importeFactura) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_FACTURA);
		this.importeFactura = importeFactura;
	}


	public double getImporteFactura() {
		return importeFactura;
	}
}
