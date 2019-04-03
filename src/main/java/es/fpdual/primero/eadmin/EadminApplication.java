package es.fpdual.primero.eadmin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.fpdual.primero.eadmin.modelo.DocumentoContable;
import es.fpdual.primero.eadmin.modelo.DocumentoFactura;



@SpringBootApplication
public class EadminApplication {

	private static final Logger logger = LogManager.getLogger(EadminApplication.class);
	
	public static void main(String[] args) {
		
		logger.info("Iniciando servicio");
		
		SpringApplication.run(EadminApplication.class, args);
		
		DocumentoContable documentoContable = new DocumentoContable(0, null, null, null, null);
		DocumentoFactura documentoFactura = new DocumentoFactura(0, null, null, null, 0.0);
		
		logger.info("Finalizando servicio");
		
		
	}
}
