package es.fpdual.primero.eadmin.servicioTest;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.primero.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.primero.eadmin.servicio.ServicioDocumentoImpl;
import modelo.Documento;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

public class ServicioDocumentoImplTest {
	
	RepositorioDocumento repositorioDocumento;
	
	ServicioDocumentoImpl servicioDocumento;
	
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
		
		Documento documento = mock(Documento.class);
		
		Documento documentoDevuletoPorElRepositorio = mock(Documento.class);
		
		when(repositorioDocumento.modificarDocumento(documento)).thenReturn(documentoDevuletoPorElRepositorio);
		
		final Documento resultado = servicioDocumento.modificarDocumento(documento);
		
		assertEquals(documentoDevuletoPorElRepositorio, resultado);
		
	}
	
	@Test
	public void deberiaEliminarUnDocumentoConCodigoFacilitado() {
		
		this.servicioDocumento.eliminarDocumento(2);
		
		verify(this.repositorioDocumento).eliminarDocumento(2);
		
	}
}
