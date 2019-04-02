package es.fpdual.primero.eadmin.controlador;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.servicio.ServicioDocumento;

public class ControladorDocumentoTest {
	
	private ControladorDocumento controlador;
	private ServicioDocumento servicioDocumento;
	private DocumentoRequest documentoRequest;
	
	@Before
	public void ejecutarAntesDeCadaTest() {
		this.servicioDocumento = mock(ServicioDocumento.class);
		this.controlador = new ControladorDocumento(servicioDocumento);
		this.documentoRequest = mock(DocumentoRequest.class);
	}
	
	@Test
	public void deberiaDarDeAltaUnDocumento() {
		
		Documento documentoInsertado = mock(Documento.class);
		
		when(this.servicioDocumento.altaDocumento(any())).thenReturn(documentoInsertado);
		
		when(this.documentoRequest.getUsuario()).thenReturn("20");
		when(this.documentoRequest.getTipoDocumento()).thenReturn("DOCUMENTO_CONTABLE");
		
		final Documento resultado = this.controlador.altaDocumento(this.documentoRequest);
		
		assertSame(documentoInsertado, resultado);
		
	}
}
