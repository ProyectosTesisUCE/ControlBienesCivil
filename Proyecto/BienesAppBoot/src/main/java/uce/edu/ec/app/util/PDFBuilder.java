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

import uce.edu.ec.app.model.Bien;

@Component
public class PDFBuilder extends AbstractITextPdfView {

	Font ffont = new Font(FontFamily.HELVETICA, 10f, Font.BOLD);
	Font ffont2 = new Font(FontFamily.HELVETICA, 8f);
	
	private int contador = 1;

	SimpleDateFormat dateFormatLarg = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			PdfWriter.getInstance(doc, new FileOutputStream("Bienes.pdf"));
			doc.open();
			// get data model which is passed by the Spring container
			List<Bien> bienes = (List<Bien>) model.get("bienes");

			// Design ParaGraph

			Font blue = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLUE);
			Chunk blueText = new Chunk("CARRERA DE INGENIERÍA CIVIL - REPORTE DE BIENES                         "
					+ "Hora: " + dateFormatLarg.format(new Date()), blue);
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

			PdfPTable table = new PdfPTable(19);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] { 1f, 1.3f, 1.3f, 2.1f, 2.8f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.8f, 2.1f,
					1.3f, 1.3f, 2.1f, 1.3f, 1.3f, 2.1f });
			table.setSpacingBefore(10);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// Tamaño letra Encabezados
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
			cell.setPadding(2.0f);
			cell.setBackgroundColor(new BaseColor(30, 144, 255));
			// write table header
			cell.setPhrase(new Phrase("N°", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Alta Nueva", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Alta Anterior", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Serie", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Descripción", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Fecha de Ingreso", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Costo", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Vida Útil", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Fin de Garantia", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Color", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Material", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Asignado", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Causionado", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Estado", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Estatus", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Guarda Almacén", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Marca", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Modelo", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Tipo", font));
			table.addCell(cell);

			try {

				for (Bien bien : bienes) {
					cell = new PdfPCell(new Phrase(String.valueOf(contador), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getAlta(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getAnterior(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getSerie(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDescripcion(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(String.valueOf(dateFormat.format(bien.getFecha_ingreso())), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(String.valueOf(bien.getCosto()), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(String.valueOf(bien.getVida_util()) + " años", ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(String.valueOf(dateFormat.format(bien.getGarantia())), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getColor(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getMaterial(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getAsignado(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getCausionado(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getEstado(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getEstatus(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getGuarda_almacen(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getMarca(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getModelo(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(bien.getDetalle().getTipo(), ffont));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
					contador++;
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