package es.fpdual.primero.eadmin.Qr;


import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import es.fpdual.primero.eadmin.modelo.Documento;
import es.fpdual.primero.eadmin.modelo.TipoDocumento;
import es.fpdual.primero.eadmin.modelo.Usuario;

public class CrearQr {

	public static void CrearQrPdf() { 

		Date fecha = new Date();
		Usuario usuario = new Usuario(1, "Pepe Martín", "jefe");
		Documento documento1= new Documento(1, "Documento 1", usuario, fecha, TipoDocumento.DOCUMENTO_CONTABLE);
		
		Document codigoQR = new Document(new Rectangle(360, 852));
		try {
			PdfWriter writer = PdfWriter.getInstance(codigoQR, new FileOutputStream("codigoQR.pdf"));
			codigoQR.open();
			BarcodeQRCode codigo = new BarcodeQRCode(documento1.getNombre(), 0, 0, null);
			Image qr_image = codigo.getImage();
			codigoQR.add(qr_image);
			codigoQR.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
