package tqs.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int routeId;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "date")
    private String date;

    @Column(name = "timeorigin")
    private String time;

    @Column(name = "timedestination")
    private String timeDestination;

    @Column(name = "duration")
    private double duration;

    @Column(name = "price")
    private double price;

    @Column(name = "ocupation")
    private int ocupation;


    @Column(name = "busplate")
    private String busplate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "busplate", referencedColumnName = "plate", insertable = false, updatable = false)
    private Bus bus;

    public int getId() {
        return routeId;
    }

    public void setId(int routeId) {
        this.routeId = routeId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(String timeDestination) {
        this.timeDestination = timeDestination;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOcupation() {
        return ocupation;
    }

    public void setOcupation(int ocupation) {
        this.ocupation = ocupation;
    }


    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getBusPlate() {
        return busplate;
    }

    public void setBusPlate(String busplate) {
        this.busplate = busplate;
    }
}
