package airplanes.entity;

import airplanes.entity.flight.Flight;

import javax.persistence.*;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private FlightPrice flight;

    public Bookmark() {}

    public Bookmark(User user, FlightPrice flight) {
        this.user = user;
        this.flight = flight;
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

    public FlightPrice getFlight() {
        return flight;
    }

    public void setFlight(FlightPrice flight) {
        this.flight = flight;
    }
}
