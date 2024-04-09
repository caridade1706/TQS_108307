package tqs.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RouteTest {
    
    private Bus bus;
    private Route route;

    @BeforeEach
    void setUp() {
        route = new Route();
        bus = new Bus("AA0000", 50, "TST", "Mercedes", 2021);

    }

    @Test
    void createARoute() {
        assertNotNull(route);
        
        route.setId(0);
        route.setBusPlate("AA0000");
        route.setOrigin("Porto");
        route.setDestination("Lisboa");
        route.setDate("2021-05-01");
        route.setTime("12:00");
        route.setTimeDestination("14:00");
        route.setDuration(50);
        route.setPrice(100);
        route.setOcupation(50);
        route.setBus(bus);

        assertEquals(0, route.getId());
        assertEquals("AA0000", route.getBusPlate());
        assertEquals("Porto", route.getOrigin());
        assertEquals("Lisboa", route.getDestination());
        assertEquals("2021-05-01", route.getDate());
        assertEquals("12:00", route.getTime());
        assertEquals("14:00", route.getTimeDestination());
        assertEquals(50, route.getDuration());
        assertEquals(100, route.getPrice());
        assertEquals(50, route.getOcupation());
        assertEquals(bus, route.getBus());

    }

    @Test
    void testConstructor(){

        route = new Route(0,"Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50,"AA0000", bus);

        assertEquals(0, route.getId());
        assertEquals("AA0000", route.getBusPlate());
        assertEquals("Porto", route.getOrigin());
        assertEquals("Lisboa", route.getDestination());
        assertEquals("2021-05-01", route.getDate());
        assertEquals("12:00", route.getTime());
        assertEquals("14:00", route.getTimeDestination());
        assertEquals(50, route.getDuration());
        assertEquals(100, route.getPrice());
        assertEquals(50, route.getOcupation());
        assertEquals(bus, route.getBus());

    }
}
