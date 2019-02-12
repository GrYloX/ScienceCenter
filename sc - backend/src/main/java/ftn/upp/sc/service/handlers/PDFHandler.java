package ftn.upp.sc.service.handlers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import org.apache.lucene.document.DateTools;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import ftn.upp.sc.model.search.IndexUnit;

public class PDFHandler extends DocumentHandler {

	@Override
	public IndexUnit getIndexUnit(File file) {
		return null;
	}

	@Override
	public String getText(File file) {
		try {
			PDDocument pdDoc = PDDocument.load(file);
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(pdDoc);
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

}
