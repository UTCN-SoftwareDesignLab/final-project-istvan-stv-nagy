package airplanes.service.ticket;

import airplanes.entity.Airport;
import airplanes.entity.Reservation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

            content.setLeading(35.5f);
            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.newLineAtOffset(200, 725);

            content.showText("--AirplaneTicket--");

            content.newLine(); content.newLine();
            content.newLineAtOffset(-125, 0);

            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.showText("1. Ticket Holder:");
            content.setFont(PDType1Font.TIMES_ITALIC, 22);
            content.newLine();
            content.showText(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName());

            content.newLine(); content.newLine();

            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.showText("2. Departure Information:");
            content.setFont(PDType1Font.TIMES_ITALIC, 22);
            content.newLine();
            Airport departureAirport = reservation.getFlight().getDepartureLocation();
            content.showText("Airport: " + departureAirport.getLocation() + "(" + departureAirport.getName() + ")");
            content.newLine();
            content.showText("Date: " + reservation.getFlight().getDepartureDate().toString());
            content.newLine();
            content.showText("Time: " + reservation.getFlight().getDepartureTime().toString());

            content.newLine(); content.newLine();

            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.showText("3. Arrival Information:");
            content.setFont(PDType1Font.TIMES_ITALIC, 22);
            content.newLine();
            Airport arrivalAirport = reservation.getFlight().getArrivalLocation();
            content.showText("Airport: " + arrivalAirport.getLocation() + "(" + arrivalAirport.getName() + ")");
            content.newLine();
            content.showText("Date: " + reservation.getFlight().getArrivalDate().toString());
            content.newLine();
            content.showText("Time: " + reservation.getFlight().getArrivalTime().toString());

            content.newLine(); content.newLine();

            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.showText("4. Ticket Price:");
            content.setFont(PDType1Font.TIMES_ITALIC, 22);
            content.newLine();
            DecimalFormat df = new DecimalFormat("#.00");
            content.showText("USD " + df.format(reservation.getPrice()) + "$");

            content.newLine(); content.newLine();

            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.showText("5. Booking Date:");
            content.setFont(PDType1Font.TIMES_ITALIC, 22);
            content.newLine();

            content.showText(reservation.getBookingDate().toString());

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
