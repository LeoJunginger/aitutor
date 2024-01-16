package de.hnu.eae.ai;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Nera, 07.01.2024: Changed to Read PDF with PDFBox, former implementation extracted null from Example PDFs

public class DocumentProcessor {

    public String processDocument(String filePath) {
        System.out.println(System.getProperty("user.dir"));

        // ClassLoader classLoader = DocumentProcessor.class.getClassLoader();
        // InputStream inputStream = classLoader.getResourceAsStream(filePath);
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            try {
            PDDocument document = PDDocument.load(new File("src/main/java/de/hnu/eae/ai/pdfs/UnderstandingLibertyServer.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
            } catch (IOException ex) {
                return "Liberty Server is a lightweight, dynamic application server designed for both development \n" + //
                        "and production environments. It is a product of IBM and is often used for cloud-native \n" + //
                        "applications, microservices, and Java applications..";
            }
        }
    }
}
