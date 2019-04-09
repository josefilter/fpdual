package es.fpdual.primero.eadmin.repositorio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Repository;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.fpdual.primero.eadmin.EadminApplication;
import es.fpdual.primero.eadmin.Qr.CrearQr;
import es.fpdual.primero.eadmin.modelo.AdministracionElectronicaException;
import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.modelo.DocumentoContable;
import es.fpdual.primero.eadmin.modelo.TipoDocumento;

@Repository
public class RepositorioDocumentoEnLista implements RepositorioDocumento {

	private final List<Documento> documentos = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(EadminApplication.class);

	@Override
	public void altaDocumento(Documento documento) {
		
		documentos.add(documento);
		this.excel();
		try {
			String nombre = (documento.getNombre() + "-" + documento.getTipoDocumento() + ".txt");
			File fichero2 = new File(nombre);
			
			if (fichero2.exists()) {
				logger.error("El fichero YA existe");
			} else {
				logger.error("El fichero no existe");
				FileWriter fichero = new FileWriter(documento.getNombre() + "-" + documento.getTipoDocumento() + ".txt");
				PrintWriter impFichero = new PrintWriter(fichero);
				
				impFichero.println("ID: " + documento.getId());
				impFichero.println("Nombre: " + documento.getNombre());
				impFichero.println("Usuario: " + documento.getUsuario());
				impFichero.println("Fecha Creacion: " + documento.getFechaCreacion());
				impFichero.println("Tipo Documento: " + documento.getTipoDocumento());

				impFichero.close();
			}
			if(documento.getTipoDocumento() == TipoDocumento.DOCUMENTO_CONTABLE) {
				this.crearPdf();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.trace("\n**********************************************");
		logger.trace("\nDocumeto creado correctamente");
		logger.trace("\nID: " + documento.getId());
		logger.trace("\nNOMBRE: " + documento.getNombre());
		logger.trace("\nUSUARIO: " + documento.getUsuario());
		logger.trace("\nFECHA CREACION: " + documento.getFechaCreacion());
		logger.trace("\nTIPO DOCUMENTO: " + documento.getTipoDocumento());
		logger.trace("\n**********************************************");
	}

	@Override
	public void modificarDocumento(Documento documento) {

		if (!documentos.contains(documento)) {
			// throw new AdministracionElectronicaException("El documento no existe.");
			logger.warn("El documento no existe", new AdministracionElectronicaException("El documento no existe"));
		}

		documentos.set(documentos.indexOf(documento), documento);
		logger.info(documento.toString());
	}

	@Override
	public void eliminarDocumento(int id) {

		// solucion 1
		Documento documentoAEliminar = new Documento(id, null, null, null, null);
		// solucion 2
		// documentoAEliminar = documentos.stream().filter(d -> d.getId() ==
		// id).findAny().orElse(null);

		final int indice = documentos.indexOf(documentoAEliminar);

		if (indice > -1) {
			documentos.remove(indice);
		}
	}

	@Override
	public List<Documento> obtenerTodosLosDocumento() {
		
		Document documento2 = new Document();
		
		try {
		FileWriter fichero = new FileWriter("ListaDocumento.txt");
		PrintWriter impFichero = new PrintWriter(fichero);
		
		for(Documento documento: documentos) {
			impFichero.println("-------------------------------");
			impFichero.println("ID: " + documento.getId());
			impFichero.println("Nombre: " + documento.getNombre());
			impFichero.println("Usuario: " + documento.getUsuario());
			impFichero.println("Fecha Creacion: " + documento.getFechaCreacion());
			impFichero.println("Tipo Documento: " + documento.getTipoDocumento());
		}
		
		impFichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.documentos.stream().collect(Collectors.toList());
	}

	@Override
	public int getSiguienteID() {

		if (documentos.isEmpty()) {
			return 1;
		}
		return documentos.get(documentos.size() - 1).getId() + 1;
	}

	@Override
	public Documento obtenerDocumentoPorId(int codigoDocumento) {

		return documentos.stream().filter(d -> d.getId() == codigoDocumento).findFirst().orElse(null);
	}
	
	public void crearPdf() {
		
		Document documento = new Document();
		
		try {
			FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");

			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			
			documento.open();
			for(Documento document: documentos) {
				documento.add(new Paragraph("id: " + document.getId()));
				documento.add(new Paragraph("nombre: " + document.getNombre()));
				documento.add(new Paragraph("usuario: " + document.getUsuario()));
				documento.add(new Paragraph("fecha creacion: " + document.getFechaCreacion()));
				documento.add(new Paragraph("Tipo Documento: " + document.getTipoDocumento()));
			}
			
			documento.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void excel() {
        // Creamos el archivo donde almacenaremos la hoja
        // de calculo, recuerde usar la extension correcta,
        // en este caso .xlsx
        File archivo = new File("documentos.xlsx");
        
        // Creamos el libro de trabajo de Excel formato OOXML
        Workbook workbook = new XSSFWorkbook(); 
        
        // La hoja donde pondremos los datos
        Sheet pagina = workbook.createSheet("Documentos");
        
        // Creamos el estilo paga las celdas del encabezado
        CellStyle style = workbook.createCellStyle();
        // Indicamos que tendra un fondo azul aqua
        // con patron solido del color indicado
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        
        String[] titulos = {"Id", "Nombre", "Usuario", "Fecha Creación", "Tipo Documento"};       
        
        // Creamos una fila en la hoja en la posicion 0
        Row fila = pagina.createRow(0);
        
	
        // Creamos el encabezado
        for(int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la posicion 
            // indicada por el contador del ciclo
            Cell celda = fila.createCell(i);
            
            // Indicamos el estilo que deseamos 
            // usar en la celda, en este caso el unico 
            // que hemos creado
            celda.setCellStyle(style); 
            celda.setCellValue(titulos[i]);
        }
        
        // Ahora creamos una fila en la posicion 1
        int j=1;
        fila = pagina.createRow(j);
        
        // Y colocamos los datos en esa fila        for(int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la
            // posicion indicada por el contador del ciclo
        	List<Documento> listaDocumentos = obtenerTodosLosDocumento();
            for (Documento documento : listaDocumentos) {
                fila.createCell(0).setCellValue(documento.getId());
                fila.createCell(1).setCellValue(documento.getNombre());
                fila.createCell(2).setCellValue(documento.getUsuario().getId());
                fila.createCell(3).setCellValue(documento.getFechaCreacion().toString());
                fila.createCell(4).setCellValue(documento.getTipoDocumento().toString());
                j++;
                fila = pagina.createRow(j);
            
			}
        
        // Ahora guardaremos el archivo
        try {
            // Creamos el flujo de salida de datos,
            // apuntando al archivo donde queremos 
            // almacenar el libro de Excel
            FileOutputStream salida = new FileOutputStream(archivo);
            
            // Almacenamos el libro de 
            // Excel via ese 
            // flujo de datos
            workbook.write(salida);
            
            // Cerramos el libro para concluir operaciones
            workbook.close();
            
            logger.info("Archivo creado existosamente");
            
        } catch (FileNotFoundException ex) {
        	logger.info("Archivo no localizable en sistema de archivos");
        } catch (IOException ex) {
        	logger.info("Error de entrada/salida");
        }
    } 

}
