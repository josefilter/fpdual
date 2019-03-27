package es.fpdual.primero.eadmin.servicio;

import java.util.List;

import modelo.Documento;

public interface ServicioDocumento {

	public Documento altaDocumento(Documento documento);
	public Documento modificarDocumento(Documento documento);
	public void eliminarDocumento(int codigoDocumento);
	public List<Documento> obtenerTodosLosDocumento();
}
