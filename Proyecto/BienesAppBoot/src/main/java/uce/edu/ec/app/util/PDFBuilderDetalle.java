package uce.edu.ec.app.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import uce.edu.ec.app.model.Bienes_Estaciones;

@Component
public class PDFBuilderDetalle extends AbstractITextPdfView {

	SimpleDateFormat dateFormatLarg = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private int contador = 1;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			PdfWriter.getInstance(doc, new FileOutputStream("DetallesPorSala.pdf"));
			doc.open();
			// get data model which is passed by the Spring container
			List<Bienes_Estaciones> bienes_Estaciones = (List<Bienes_Estaciones>) model.get("bienes_Estaciones");

			// Design ParaGraph

			Font blue = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE);
			Chunk blueText = new Chunk("CARRERA DE INGENIERÍA CIVIL- DETALLE DE BIENES POR ESTACIÓN     " + "Hora: "
					+ dateFormatLarg.format(new Date()), blue);
			Paragraph p = new Paragraph(blueText);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			// Added image in PDF
			try {
				Image img = Image.getInstance("C:\\logoUce.jpg");
				img.scalePercent(100f);
				img.scaleAbsolute(120f, 60f);
				doc.add(img);
			} catch (Exception e) {

			}

			PdfPTable table = new PdfPTable(14);
			table.setWidthPercentage(100.0f);
			table.setWidths(
					new float[] { 1.0f, 1.5f, 1.0f, 1.0f, 2.0f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f });
			table.setSpacingBefore(10);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// define font for table header row
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
			font.setColor(BaseColor.WHITE);

			// Tamaño letra Contenido Celdas
			Font ffont = new Font(FontFamily.HELVETICA, 10);

			// define table header cell
			PdfPCell cell = new PdfPCell(new Paragraph("Created By Basant"));
			/*
			 * previous code blue pattern cell.setBackgroundColor(BaseColor.BLUE);
			 * cell.setPadding(6);
			 */

			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(30, 144, 255));
			// write table header
			cell.setPhrase(new Phrase("N°", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Persona Usa", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Alta Nueva", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Alta Anterior", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Descripción", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Marca", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Modelo", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Serie", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Guarda Almacén", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Causionado", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Ubicación", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Lugar", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Registro", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Actualización", font));
			table.addCell(cell);

			// write table row data
			try {
				for (Bienes_Estaciones bien_Estacion : bienes_Estaciones) {
					cell = new PdfPCell(new Phrase(String.valueOf(contador), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDetalle().getAsignado(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getAlta(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getAnterior(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDescripcion(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDetalle().getMarca(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDetalle().getModelo(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getSerie(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDetalle().getGuarda_almacen(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getBien().getDetalle().getCausionado(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getEstacion().getUbicacion(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien_Estacion.getEstacion().getLugar(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(
							new Phrase(String.valueOf(dateFormat.format(bien_Estacion.getRegistro())), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(
							new Phrase(String.valueOf(dateFormat.format(bien_Estacion.getActualizacion())), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					
					contador ++;

				}
				doc.add(table);
				doc.close();
			} catch (Exception e) {

			}

		} catch (DocumentException ex) {

		} catch (java.io.IOException ex) {
		}
	}
}