package es.fpdual.primero.eadmin.repositorio;

import org.junit.Before;
import org.junit.Test;


import es.fpdual.primero.eadmin.modelo.AdministracionElectronicaException;
import es.fpdual.primero.eadmin.modelo.Documento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class RepositorioDocumentoEnListaTest {

	private RepositorioDocumentoEnLista repositorioDocumento;
	
	private Documento documento;

	@Before
	public void inicializarEnCadaTest() {
		this.repositorioDocumento = new RepositorioDocumentoEnLista();
		this.documento = mock(Documento.class);
	}

	@Test
	public void deberiaAlmacenarUnNuevoDocumento() {

		when(documento.getNombre()).thenReturn("documento 1");
		when(documento.getId()).thenReturn(5);

		this.repositorioDocumento.altaDocumento(documento);

		assertTrue(this.repositorioDocumento.obtenerTodosLosDocumento().contains(documento));
	}
	
	@Test (expected = AdministracionElectronicaException.class)
	public void deberiaLanzarUnaExcepcionAlAlmacenarUnDocumentoYaExistente() {
		
		when(documento.getNombre()).thenReturn("documento 1");
		when(documento.getId()).thenReturn(5);
		
		this.repositorioDocumento.altaDocumento(documento);
		this.repositorioDocumento.altaDocumento(documento);
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		
		Documento documentoAlmacenado = new Documento(20, "doc1", null, null, null);
		Documento documentoModificado = new Documento(20, "doc2", null, null, null);
		
		this.repositorioDocumento.altaDocumento(documentoAlmacenado);
		this.repositorioDocumento.modificarDocumento(documentoModificado);
		
		assertEquals("doc2", this.repositorioDocumento.obtenerTodosLosDocumento().get(0).getNombre());
	}
	
	@Test (expected = AdministracionElectronicaException.class)
	public void deberiaLanzarUnaExcepcionAlModificarUnDocumentoNoExistente() {
		
		this.repositorioDocumento.modificarDocumento(documento);
	}
	
	@Test
	public void deberiaEliminarDocumento() {

		when(documento.getId()).thenReturn(5);
		when(documento.getNombre()).thenReturn("documentoAEliminar");
		
		this.repositorioDocumento.altaDocumento(documento);
		this.repositorioDocumento.eliminarDocumento(5);
		
		//Todos funcionan
//		assertFalse(this.repositorioDocumento.obtenerTodosLosDocumento().contains(documento));
		assertTrue(this.repositorioDocumento.obtenerTodosLosDocumento().isEmpty());
//		assertEquals(this.repositorioDocumento.obtenerTodosLosDocumento().size(), 0);
	}
	
	@Test
	public void deberiaNoHacerNadaSiElDocumentoNoExiste() {
		
		this.repositorioDocumento.eliminarDocumento(20);
	}
	
	@Test
	public void deberiaDevolverElSiguienteID() {
		
		when(documento.getId()).thenReturn(20);
		when(documento.getNombre()).thenReturn("documento nuevo");
		
		this.repositorioDocumento.altaDocumento(documento);
		final int resultado = this.repositorioDocumento.getSiguienteID();
		
		assertEquals(21, resultado);
	}
	
	@Test
	public void deberiaDevolverUnoSiLaListaEstaVacia() {
		
		final int resultado = this.repositorioDocumento.getSiguienteID();
		
		assertEquals(1, resultado);
	}
}
