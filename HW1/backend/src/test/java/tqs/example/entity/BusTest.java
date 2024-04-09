package tqs.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BusTest {

        private Bus bus;

        @BeforeEach
        void setUp() {
            bus = new Bus();
        }

        

        @Test
        void createABus() {
            assertNotNull(bus);
            bus = new Bus("AA0000", 50, "TST", "Mercedes", 2021);

            assertEquals("AA0000", bus.getPlateBus());
            assertEquals(50, bus.getSeatsBus());
            assertEquals("TST", bus.getCompanyBus());
            assertEquals("Mercedes", bus.getModelBus());
            assertEquals(2021, bus.getYearBus());
        }
        
        @Test
        void setBus() {
            Bus bus = new Bus();
            bus.setPlateBus("AA0058");
            bus.setSeatsBus(50);
            bus.setCompanyBus("TST");
            bus.setModelBus("Mercedes");
            bus.setYearBus(2021);

            assertEquals("AA0058", bus.getPlateBus());
            assertEquals(50, bus.getSeatsBus());
            assertEquals("TST", bus.getCompanyBus());
            assertEquals("Mercedes", bus.getModelBus());
            assertEquals(2021, bus.getYearBus());
        }
            
}
