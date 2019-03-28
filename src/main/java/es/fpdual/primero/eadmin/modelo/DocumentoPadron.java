package es.fpdual.primero.eadmin.modelo;

import java.util.Date;

public class DocumentoPadron extends Documento {
	
	private final int numeroPadron;
	

	public DocumentoPadron(int id, String nombre, Usuario usuario, Date fechaCreacion, int numeroPadron) {
		super(id, nombre, usuario, fechaCreacion, TipoDocumento.DOCUMENTO_PADRON);
		this.numeroPadron = numeroPadron;
	}

	public int getTotalHabitantes() {
		return numeroPadron;
	}
}
