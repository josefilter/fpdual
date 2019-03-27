package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Expediente extends AdministracionElectronicaBase {

	private final TipoExpediente tipoExpediente;
	private final List<Documento> documento;
	
	public Expediente(int id, String nombre, Usuario usuario, Date fechaCreacion, TipoExpediente tipoExpediente, List<Documento> documento) {
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
		this.fechaCreacion = fechaCreacion;
		this.tipoExpediente = tipoExpediente;
		this.documento = documento;
	}

	public TipoExpediente getTipoDocumento() {
		return tipoExpediente;
	}
	
	public List<Documento> getDocumento(){
		return this.documento;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Expediente) {
			final Expediente expediente = (Expediente) obj;
			return expediente.getId() == this.getId();
		}
		return false;
	}
	
	public List<Integer> obtenerLongitudNombreDocumentos() {
		
		if(documento.size() == 0) {
			throw new AdministracionElectronicaException("Lista de documentos vacia");
		}
		return documento.stream().filter(this::esDocumentoContable).map(Documento::getNombre).map(String::length).collect(Collectors.toList());
	}
	
	private boolean esDocumentoContable(Documento documentos) {
		return documentos.getTipoDocumento().equals(TipoDocumento.DOCUMENTO_CONTABLE);
	}
	
	public Map<TipoDocumento, List<Documento>> agruparListaDocumento(){
		
//		return documento.stream().collect(Collectors.groupingBy(Documento::getTipoDocumento));
		return documento.stream().collect(Collectors.groupingBy(d -> d.getTipoDocumento()));
	}
}
