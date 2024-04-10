package tqs.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    @Test
    void createAPerson() {
        assertNotNull(person);
        person = new Person(0, "John Doe", "john@email.com", "123456789", "Street", "12345", "Seattle");

        assertEquals(0, person.getIdPerson());
        assertEquals("John Doe", person.getNamePerson());
        assertEquals("john@email.com", person.getEmailPerson());
        assertEquals("123456789", person.getPhonePerson());
        assertEquals("Street", person.getAddressPerson());
        assertEquals("12345", person.getZipPerson());
        assertEquals("Seattle", person.getCityPerson());
        
    }
    
    @Test
    void testGettersAndSetters() {
        person.setIdPerson(1);
        person.setNamePerson("Jane Doe");
        person.setEmailPerson("jane@email.com");
        person.setPhonePerson("987654321");
        person.setAddressPerson("Avenue");
        person.setZipPerson("54321");
        person.setCityPerson("New York");

        assertEquals(1, person.getIdPerson());
        assertEquals("Jane Doe", person.getNamePerson());
        assertEquals("jane@email.com", person.getEmailPerson());
        assertEquals("987654321", person.getPhonePerson());
        assertEquals("Avenue", person.getAddressPerson());
        assertEquals("54321", person.getZipPerson());
        assertEquals("New York", person.getCityPerson());
    }
}
