package airplanes.service.ticket;

import airplanes.entity.Reservation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class TicketGeneratorPDF implements TicketGenerator {

    @Override
    public byte[] generateTicket(Reservation reservation) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();

            content.setLeading(20.5f);
            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.newLineAtOffset(25, 700);

            content.showText("Ticket:");

            content.endText();
            content.close();

            doc.save(byteArrayOutputStream);

            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
