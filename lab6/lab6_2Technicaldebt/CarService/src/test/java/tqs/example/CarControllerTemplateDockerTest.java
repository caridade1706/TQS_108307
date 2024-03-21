package tqs.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import tqs.example.Entity.Car;
import tqs.example.Repository.CarRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CarControllerTemplateDockerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void setUp() {
        carRepository.deleteAll();
    }

    @Test
    public void whenGetAllCars_thenReturnCars() {
        Car audi = new Car("Audi", "A3");
        Car bmw = new Car("BMW", "M3");

        carRepository.save(audi);
        carRepository.save(bmw);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(2);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("A3", "M3");  

    }

    @Test
    public void whenPostCar_thenCreateCar() {
        Car audi = new Car("Audi", "A3");

        ResponseEntity<Car> response = restTemplate.postForEntity("/api/create", audi, Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getMaker()).isEqualTo("Audi");
        assertThat(response.getBody().getModel()).isEqualTo("A3");
    }

    @Test
    public void whenGetCarById_thenReturnCar() {
        Car audi = new Car("Audi", "A3");
        carRepository.save(audi);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/car/" + audi.getCarId(), Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMaker()).isEqualTo("Audi");
        assertThat(response.getBody().getModel()).isEqualTo("A3");
    }

    @Test
    public void whenGetCarById_thenReturnNotFound() {
        ResponseEntity<Car> response = restTemplate.getForEntity("/api/car/1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    
    
}
