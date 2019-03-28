package es.fpdual.primero.eadmin.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.util.SocketUtils;

import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.modelo.DocumentoContable;
import es.fpdual.primero.eadmin.modelo.Expediente;
import es.fpdual.primero.eadmin.modelo.TipoDocumento;
import es.fpdual.primero.eadmin.modelo.TipoExpediente;
import es.fpdual.primero.eadmin.modelo.Usuario;

public class ExpedienteTest {

	@Test
	public void deberiaDevolverTrueSiLosExpedientesSonIguales() {
		
		//inicialización
		final Expediente expediente1 = new Expediente(1,null,null,null,null,null);
		final Expediente expediente2 = new Expediente(1,null,null,null,null,null);

		//ejecución
		final boolean resultado = expediente1.equals(expediente2);

		//comprobación de resultados
		assertTrue(resultado);
	}
	
	@Test
	public void deberiaCalcularHashCode() {
		
		final Expediente expediente1 = new Expediente(1,null,null,null,null,null);
		
		final int resultado = expediente1.hashCode();
		
		assertEquals(1, resultado);
	}
	
	@Test
	public void deberiaConstruirUnExpedienteConDocumentos() {
		
		//inicialización
		final Usuario usuario = new Usuario(1,"juan", "alcalde");
		
		final DocumentoContable documentoContable = new DocumentoContable(10,"operacion contable", usuario, new Date(), "52344454552332");
		
		final Documento documento = new DocumentoContable(15,"operacion", usuario, new Date(), "566565464259");
		
		final Documento documentoNuevo = new Documento(20,"nuevo documento", usuario, new Date(), TipoDocumento.DOCUMENTO_FACTURA);
		
		final List<Documento> documentos = new ArrayList<Documento>();
		documentos.add(documento);
		documentos.add(documentoContable);
		documentos.add(documentoNuevo);
		
//		for (Documento documentoActual:documentos) {
//			if (esDocumentoContable(documentoActual)) {
//				System.out.println(documentoActual);
//			} 	
//		}
		
//		documentos.stream().filter(documentoActual -> esDocumentoContable(documentoActual)).forEach(documentoActual -> System.out.println(documentoActual));
		
		documentos.stream().filter(this::esDocumentoContable).forEach(System.out::println);
		
//		documentos.stream().map(documentoActual -> documentoActual.getNombre()).forEach(documentoActual -> System.out.println(documentoActual));
		
		documentos.stream().map(Documento::getNombre).forEach(System.out::println);
		
//		documentos.stream().map(documentoActual -> documentoActual.getNombre()).map(nombre -> nombre.length()).forEach(longitud -> System.out.println(longitud));
		
//		documentos.stream().map(Documento::getNombre).map(String::length).forEach(System.out::println);
		
		for(Documento documentoActual:documentos) {
			System.out.println(documentoActual.getNombre().length());
		}
			
			
		final List<Documento> documetosContable = documentos.stream().filter(this::esDocumentoContable).collect(Collectors.toList());
		
		//ejecución
		final Expediente expediente = new Expediente(1,"exp 1", usuario, new Date(), TipoExpediente.EXPEDIENTE_LICENCIAS_OBRAS, documentos);
		
		//comprobación de resultados
		assertEquals(documentos, expediente.getDocumento());
		
		assertTrue(expediente.getDocumento().contains(documentoContable));
		
		assertEquals(3, expediente.getDocumento().size());
		
		
		
	}
	
	public boolean esDocumentoContable(Documento documentos) {
		return documentos.getTipoDocumento().equals(TipoDocumento.DOCUMENTO_CONTABLE);
	}
	
	
	@Test
	public void deberiaDevolverLaLongitudDeLosNombresDeLosDocumentos() {
		
		//inicialización
		final Usuario usuario = new Usuario(1,"juan", "alcalde");
		final Date fecha = new Date();
		final Documento documento1 = new Documento(1,"uno", usuario, fecha, TipoDocumento.DOCUMENTO_CONTABLE);
		final Documento documento3 = new Documento(3,"cuatro", usuario, fecha, TipoDocumento.DOCUMENTO_CONTABLE);
		final List<Documento> documentos = new ArrayList<>();
		documentos.add(documento1);
		documentos.add(documento3);
		
		final Expediente expediente1 = new Expediente(1,"nombre",usuario,fecha,TipoExpediente.EXPEDIENTE_SUBVENCION,documentos);

		//ejecución
		final List<Integer> resultado = expediente1.obtenerLongitudNombreDocumentos();

		//comprobación de resultados
		assertEquals(2, resultado.size());
		assertEquals(3, resultado.get(0).intValue());
//		assertEquals(new Integer(6), resultado.get(1));
		assertEquals(Integer.parseInt("6"), resultado.get(1).intValue());
	}
	
	@Test
	public void deberiaDevolverDocumentosAgrupadosPorTipo() {
		final Usuario usuario = new Usuario(1,"juan", "alcalde");
		final Date fecha = new Date();
		final Documento documento1 = new Documento(1,"doc uno", usuario, fecha, TipoDocumento.DOCUMENTO_CONTABLE);
		final Documento documento2 = new Documento(2,"doc 2", usuario, fecha, TipoDocumento.DOCUMENTO_CONTABLE);
		final Documento documento3 = new Documento(3,"doc tres", usuario, fecha, TipoDocumento.DOCUMENTO_NOMINA);
		
		
	}
	
	
}
