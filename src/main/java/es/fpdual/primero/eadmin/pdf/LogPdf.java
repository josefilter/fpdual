package es.fpdual.primero.eadmin.pdf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class LogPdf {

	private static final Logger logger = LogManager.getLogger(LogPdf.class);

	public static void logToPdf() {

		try {
			String cadena;
			FileReader file = new FileReader("Salida.log");
			BufferedReader buf = new BufferedReader(file);
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream("Salida.pdf"));

			documento.open();
			Font font = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Log EadminApplication", font);
			Paragraph parrafo = new Paragraph();
			try {
				while ((cadena = buf.readLine()) != null) {
					parrafo.add(cadena + "\n");
				}
			} catch (IOException e) {
				logger.error(e);
			}
			documento.add(chunk);
			documento.add(parrafo);
			documento.close();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (DocumentException e) {
			logger.error(e);
		}

	}
}
