package tqs.example.init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tqs.example.entity.Bus;
import tqs.example.entity.Route;
import tqs.example.repository.BusRepository;
import tqs.example.repository.RouteRepository;

// ...

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;

    public DatabaseInitializer(BusRepository busRepository, RouteRepository routeRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
    }

    private static final String BUS_DATA_FILE = "src/main/resources/data/bus.txt";
    private static final String ROUTE_DATA_FILE = "src/main/resources/data/routes.txt";

    @Override
    public void run(String... args) throws Exception {
        // Load and persist bus data
        List<Bus> buses = parseBusesFromFile(BUS_DATA_FILE);
        busRepository.saveAll(buses);

        // Load and persist route data
        List<Route> routes = parseRoutesFromFile(ROUTE_DATA_FILE);
        routeRepository.saveAll(routes);

        // ... other initializations ...
    }

    List<Bus> parseBusesFromFile(String filePath) throws IOException {
    List<Bus> buses = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line= br.readLine(); // skip header line         
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String plate = data[0];
            // Verifica se o ônibus já existe pelo número da placa
            if (busRepository.findByPlate(plate) == null) {
                Bus bus = new Bus();
                bus.setPlateBus(plate);
                bus.setSeatsBus(Integer.parseInt(data[1]));
                bus.setCompanyBus(data[2]);
                bus.setModelBus(data[3]);
                bus.setYearBus(Integer.parseInt(data[4]));
                buses.add(bus);
            }
        }
    }
    return buses;
}

    
    List<Route> parseRoutesFromFile(String filePath) throws IOException {
        List<Route> routes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(!routeRepository.existsByOriginAndDestinationAndDate(data[0], data[1], data[2])) {
                              
                    Route route = new Route();
                    route.setOrigin(data[0]);
                    route.setDestination(data[1]);
                    route.setDate(data[2]);
                    route.setTime(data[3]);
                    route.setTimeDestination(data[4]);
                    route.setDuration(Double.parseDouble(data[5]));
                    route.setPrice(Double.parseDouble(data[6]));
                    route.setOcupation(Integer.parseInt(data[7]));
                    route.setBusPlate(data[8]);
                    route.setBus(busRepository.findByPlate(data[8]));                   
                    routes.add(route);
                }
            }
        }
        return routes;
    }
    
}
