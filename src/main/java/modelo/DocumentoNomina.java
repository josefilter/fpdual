package modelo;

import java.util.Date;

public class DocumentoNomina extends Documento {

	private final double nominasEmpleados;

	public DocumentoNomina(int id, String nombre, Usuario usuario, Date fechaCreacion, double nominasEmpleados) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_NOMINA);
		this.nominasEmpleados = nominasEmpleados;
	}

	public double getNominasEmpleados() {
		return nominasEmpleados;
	}
}
