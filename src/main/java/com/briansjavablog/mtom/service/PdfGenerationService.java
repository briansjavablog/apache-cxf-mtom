package com.briansjavablog.mtom.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfGenerationService {

	public byte[] generatePdf() {

		try {

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			Document document = new Document(PageSize.LETTER);
			PdfWriter.getInstance(document, byteArrayOutputStream);

			document.open();
			PdfPTable table = new PdfPTable(5);
			table.setHeaderRows(1);
			table.setSplitRows(false);
			table.setComplete(false);

			for (int i = 0; i < 5; i++) {
				table.addCell("Header " + i);
			}

			for (int i = 0; i < 50; i++) {
				if (i % 5 == 0) {
					document.add(table);
				}
				table.addCell("Test " + i);
			}

			table.setComplete(true);
			document.add(table);
			document.close();

			return byteArrayOutputStream.toByteArray();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}