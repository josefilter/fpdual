package es.fpdual.primero.eadmin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EadminApplication {

	private static final Logger logger = LogManager.getLogger(EadminApplication.class);
	
	public static void main(String[] args) {
		
		logger.info("Iniciando servicio");
		
		SpringApplication.run(EadminApplication.class, args);
		
		logger.info("Finalizando servicio");
	}
}
