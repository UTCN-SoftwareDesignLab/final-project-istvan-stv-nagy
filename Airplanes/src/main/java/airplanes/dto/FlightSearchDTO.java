package airplanes.dto;

public class FlightSearchDTO {

    private int departureAirportID;

    private int arrivalAirportID;

    private double minPrice;

    private double maxPrice;

    private int maxTransfers;

    public FlightSearchDTO() {}

    public int getDepartureAirportID() {
        return departureAirportID;
    }

    public void setDepartureAirportID(int departureAirportID) {
        this.departureAirportID = departureAirportID;
    }

    public int getArrivalAirportID() {
        return arrivalAirportID;
    }

    public void setArrivalAirportID(int arrivalAirportID) {
        this.arrivalAirportID = arrivalAirportID;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMaxTransfers() {
        return maxTransfers;
    }

    public void setMaxTransfers(int maxTransfers) {
        this.maxTransfers = maxTransfers;
    }
}
