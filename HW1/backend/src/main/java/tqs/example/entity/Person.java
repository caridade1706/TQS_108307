package tqs.example.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")    
    private String address;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;


    public int getIdPerson() {
        return id;
    }

    public void setIdPerson(int id) {
        this.id = id;
    }

    public String getNamePerson() {
        return name;
    }

    public void setNamePerson(String name) {
        this.name = name;
    }

    public String getEmailPerson() {
        return email;
    }

    public void setEmailPerson(String email) {
        this.email = email;
    }

    public String getPhonePerson() {
        return phone;
    }

    public void setPhonePerson(String phone) {
        this.phone = phone;
    }

    public String getAddressPerson() {
        return address;
    }

    public void setAddressPerson(String address) {
        this.address = address;
    }

    public String getZipPerson() {
        return zip;
    }

    public void setZipPerson(String zip) {
        this.zip = zip;
    }

    public String getCityPerson() {
        return city;
    }

    public void setCityPerson(String city) {
        this.city = city;
    }


    
    
    
    
}
