package tqs.example.init;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;
import java.util.*;

import org.junit.jupiter.api.Test;

import tqs.example.entity.Bus;
import tqs.example.entity.Route;
import tqs.example.repository.BusRepository;
import tqs.example.repository.RouteRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseInitializerTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private RouteRepository routeRepository;
    

    @InjectMocks
    private DatabaseInitializer databaseInitializer;

    @Test
    void testParseBusesFromFile() throws IOException {

        String testFilePath = "src/main/resources/data/bus.txt";
        when(busRepository.findByPlate(anyString())).thenReturn(null);
        List<Bus> parsedBuses = databaseInitializer.parseBusesFromFile(testFilePath);
        assertFalse(parsedBuses.isEmpty());

        parsedBuses.forEach(bus -> {
            assertNotNull(bus.getPlateBus());
        });

        verify(busRepository, times(24)).findByPlate(anyString());
    }

    @Test
    void testParseRoutesFromFile() throws IOException {

        String testFilePath = "src/main/resources/data/routes.txt";

        when(routeRepository.existsByOriginAndDestinationAndDate(anyString(), anyString(), anyString())).thenReturn(false);

        List<Route> parsedRoutes = databaseInitializer.parseRoutesFromFile(testFilePath);
        assertFalse(parsedRoutes.isEmpty());

        parsedRoutes.forEach(route -> {
            assertNotNull(route.getOrigin());
            assertNotNull(route.getDestination());
            assertNotNull(route.getDate());
        });

        verify(routeRepository, times(72))
            .existsByOriginAndDestinationAndDate(anyString(), anyString(), anyString());
    }

}
