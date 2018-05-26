package airplanes.entity;

import airplanes.entity.flight.Flight;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class FlightPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    private double price;

    public FlightPrice() {}

    public FlightPrice(User user, Flight flight, double price) {
        this.user = user;
        this.flight = flight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
