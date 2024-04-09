package tqs.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @Column(name = "plate")
    private String plate;

    @Column(name = "seats")
    private int seats;

    @Column(name = "company")
    private String company;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    public String getPlateBus() {
        return plate;
    }

    public void setPlateBus(String plate) {
        this.plate = plate;
    }

    public int getSeatsBus() {
        return seats;
    }

    public void setSeatsBus(int seats) {
        this.seats = seats;
    }

    public String getCompanyBus() {
        return company;
    }

    public void setCompanyBus(String company) {
        this.company = company;
    }

    public String getModelBus() {
        return model;
    }

    public void setModelBus(String model) {
        this.model = model;
    }

    public int getYearBus() {
        return year;
    }

    public void setYearBus(int year) {
        this.year = year;
    }

        
    
}
