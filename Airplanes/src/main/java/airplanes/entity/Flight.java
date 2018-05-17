package airplanes.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Airport departureLocation;

    @ManyToOne
    private Airport arrivalLocation;

    private Date departureDate;

    private Date arrivalDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Airport departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Airport getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Airport arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
