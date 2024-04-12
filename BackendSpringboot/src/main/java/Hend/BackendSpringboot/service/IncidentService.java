package Hend.BackendSpringboot.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import Hend.BackendSpringboot.entity.Incident;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class IncidentService {

    public void generatePdf(List<Incident> incidents, OutputStream outputStream) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("List of Incidents\n\n");

                for (Incident incident : incidents) {
                    contentStream.showText("Description: " + incident.getDescription() + "\n");
                    contentStream.showText("Date: " + incident.getDate().toString() + "\n");
                    contentStream.showText("Location: " + incident.getLocalisation() + "\n");
                    contentStream.showText("Status: " + incident.getStatus().toString() + "\n\n");
                }

                contentStream.endText();
            }

            document.save(outputStream);
        }
    }

    // Other methods related to IncidentService...
}
