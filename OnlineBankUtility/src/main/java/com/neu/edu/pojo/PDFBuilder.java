package com.neu.edu.pojo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		List<TransactionDetails> transactions = (List<TransactionDetails>) model.get("transactions");

		doc.add(new Paragraph("Transaction Statement"));

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 1.0f });
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.GRAY);
		cell.setPadding(2);

		// write table header
		cell.setPhrase(new Phrase("TransactionID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Account Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Amount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Type", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DateTime", font));
		table.addCell(cell);

		// write table row data
		for (TransactionDetails td : transactions) {
			table.addCell(String.valueOf(td.getTransactionId()));
			table.addCell(String.valueOf(td.getAccountNumber()));
			table.addCell(String.valueOf(td.getAmount()));
			table.addCell(String.valueOf(td.getTransactionType()));
			table.addCell(String.valueOf(td.getDateTime()));
		}

		doc.add(table);

	}
}