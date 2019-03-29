package es.fpdual.primero.eadmin.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.servicio.ServicioDocumento;

@RestController
public class ControladorDocumento {

	ServicioDocumento servicioDocumento;

	@Autowired
	public ControladorDocumento(ServicioDocumento servicioDocumento) {
		this.servicioDocumento = servicioDocumento;
	}
	
	@PostMapping ("/documentos")
	public Documento altaDocumento(@RequestBody DocumentoRequest documentoRequest) {
		
		final Documento documentoAlInsertar = DocumentoRequestMapper.toDocumento(documentoRequest);
		return this.servicioDocumento.altaDocumento(documentoAlInsertar);
	}
	
	@GetMapping ("/documentos")
	public List<Documento> obtenerTodosLosDocumentos() {
		
		return this.servicioDocumento.obtenerTodosLosDocumento();
	}
	
	@DeleteMapping ("/documentos")
	public void eliminarDocumento(@RequestBody Documento documento) {
		
	}
}
