package es.fpdual.primero.eadmin.repositorio;

import java.util.List;


import modelo.Documento;

public interface RepositorioDocumento {
	
	public Documento altaDocumento(Documento documento);
	public Documento modificarDocumento(Documento documento);
	public void eliminarDocumento(int codigoDocumento);
	public List<Documento> obtenerTodosLosDocumento();
	
	public int getSiguienteID();
	
}
