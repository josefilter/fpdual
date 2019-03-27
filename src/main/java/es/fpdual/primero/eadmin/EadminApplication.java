package es.fpdual.primero.eadmin;


import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import modelo.Documento;
import modelo.DocumentoContable;
import modelo.TipoDocumento;
import modelo.Usuario;


@SpringBootApplication
public class EadminApplication {

	public static void main(String[] args) {
		
		final Usuario usuario = new Usuario(4, "bob",  "chef");
		final Date fecha = new Date();
		
		final DocumentoContable documentoContable = new DocumentoContable(10,"operacion contable", usuario, fecha, "52344454552332");
		
		final Documento documento = new DocumentoContable(15,"operacion", usuario, fecha, "566565464259");

		Documento documentoNuevo;
		documentoNuevo = documentoContable;
		documentoNuevo = documento;
		
		DocumentoContable documentoContable2;
		
		documentoContable2 = (DocumentoContable) documento;
		
		
		SpringApplication.run(EadminApplication.class, args);
	}
}
