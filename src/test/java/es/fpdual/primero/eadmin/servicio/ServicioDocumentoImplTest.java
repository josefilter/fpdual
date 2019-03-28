package es.fpdual.primero.eadmin.servicio;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.primero.eadmin.servicio.ServicioDocumentoImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

public class ServicioDocumentoImplTest {
	
	RepositorioDocumento repositorioDocumento;
	
	ServicioDocumentoImpl servicioDocumento;
	
	private final Documento documento = mock(Documento.class);
	
	@Before
	public void inicializarAntesDeCadaTest() {
		this.repositorioDocumento = mock(RepositorioDocumento.class);
		this.servicioDocumento = new ServicioDocumentoImpl(repositorioDocumento);
	}
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() {
		
		List<Documento> listaDevueltaPorElRepositorio = new ArrayList<>();
		
		when(repositorioDocumento.obtenerTodosLosDocumento()).thenReturn(listaDevueltaPorElRepositorio);
		
		final List<Documento> resultado = servicioDocumento.obtenerTodosLosDocumento();
		
		assertEquals(listaDevueltaPorElRepositorio, resultado);
	}
	
	@Test
	public void deberiaModificarUnDocumentos() {
		
		servicioDocumento.modificarDocumento(documento);
		
		verify(this.repositorioDocumento).modificarDocumento(documento);
		
	}
	
	@Test
	public void deberiaEliminarUnDocumentoConCodigoFacilitado() {
		
		this.servicioDocumento.eliminarDocumento(2);
		
		verify(this.repositorioDocumento).eliminarDocumento(2);
		
	}
	
	@Test
	public void deberiaAlmacenarDocumento() {
		
		when(this.repositorioDocumento.getSiguienteID()).thenReturn(22);
		
		final Documento resultado = this.servicioDocumento.altaDocumento(documento);
		
	}
}
