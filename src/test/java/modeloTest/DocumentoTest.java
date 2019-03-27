package modeloTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.Documento;

public class DocumentoTest {
	
	@Test
	public void deberiaDevolverTrueSiLosDocumentosSonIguales() {
		
		//inicialización
		final Documento documento1 = new Documento(1,null,null,null,null);
		final Documento documento2 = new Documento(1,null,null,null,null);

		//ejecución
		final boolean resultado = documento1.equals(documento2);

		//comprobación de resultados
		assertTrue(resultado);
	}
	
	@Test
	public void deberiaCalcularHashCode() {
		
		final Documento documento1 = new Documento(1,null,null,null,null);
		
		final int resultado = documento1.hashCode();
		
		assertEquals(1, resultado);
	}
}
