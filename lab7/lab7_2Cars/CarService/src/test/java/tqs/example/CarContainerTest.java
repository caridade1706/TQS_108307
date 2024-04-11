package tqs.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.example.entities.Car;
import tqs.example.repository.CarRepository;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
public class CarContainerTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("user")
            .withPassword("password")
            .withDatabaseName("test");

        @LocalServerPort
            int port;
           
        
        @Autowired
        private CarRepository repository;
        
        @DynamicPropertySource
        static void properties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);

        }

        @BeforeEach
        public void setUpTestCars() throws Exception {
            repository.deleteAll();
        }

        @Test
        void whenValidInput_thenCreateCar() {
            Car car = new Car("Audi", "A3");
            
            given().
                port(port).
                contentType("application/json").
                body(car).
            when().
                post("/api/create").
            then().
                statusCode(201);

            List<Car> found = repository.findAll();
            assertThat(found).extracting(Car::getMaker).containsOnly("Audi");
        }

        @Test
        void whenGetCars_thenRetrieveCars() {
            Car audi = new Car("Audi", "A3");
            Car bmw = new Car("BMW", "X1");
            repository.save(audi);
            repository.save(bmw);

            given().
                port(port).
            when().
                get("/api/cars").
            then().
                statusCode(200).
                body("$.size()", equalTo(2)).
                body("[0].maker", equalTo("Audi")).
                body("[1].maker", equalTo("BMW"));
        }

        @Test
        void whenGetCar_thenRetrieveCar() {
            Car audi = new Car("Audi", "A3");
            repository.save(audi);

            given().
                port(port).
            when().
                get("/api/car/1").
            then().
                statusCode(200).
                body("maker", equalTo("Audi")).
                body("model", equalTo("A3"));
        }
    




    
}
