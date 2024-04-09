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

        assertEquals(0, person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals("john@email.com", person.getEmail());
        assertEquals("123456789", person.getPhone());
        assertEquals("Street", person.getAddress());
        assertEquals("12345", person.getZip());
        assertEquals("Seattle", person.getCity());
        
    }
    
    @Test
    void testGettersAndSetters() {
        person.setId(1);
        person.setName("Jane Doe");
        person.setEmail("jane@email.com");
        person.setPhone("987654321");
        person.setAddress("Avenue");
        person.setZip("54321");
        person.setCity("New York");

        assertEquals(1, person.getId());
        assertEquals("Jane Doe", person.getName());
        assertEquals("jane@email.com", person.getEmail());
        assertEquals("987654321", person.getPhone());
        assertEquals("Avenue", person.getAddress());
        assertEquals("54321", person.getZip());
        assertEquals("New York", person.getCity());
    }
}
