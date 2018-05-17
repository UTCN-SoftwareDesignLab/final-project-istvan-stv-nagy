package airplanes.dto;

public class FlightDTO {

    private String departureDate;
    private String departureTime;
    private int departureAirportID;

    private String arrivalDate;
    private String arrivalTime;
    private int arrivalAirportID;

    public FlightDTO(String departureDate, String departureTime, int departureAirportID, String arrivalDate, String arrivalTime, int arrivalAirportID) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureAirportID = departureAirportID;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.arrivalAirportID = arrivalAirportID;
    }

    public FlightDTO() {}

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getDepartureAirportID() {
        return departureAirportID;
    }

    public void setDepartureAirportID(int departureAirportID) {
        this.departureAirportID = departureAirportID;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalAirportID() {
        return arrivalAirportID;
    }

    public void setArrivalAirportID(int arrivalAirportID) {
        this.arrivalAirportID = arrivalAirportID;
    }
}
