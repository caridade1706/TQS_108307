package tqs.example;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import tqs.example.Controller.CarController;
import tqs.example.Entity.Car;
import tqs.example.Service.CarManagerService;

@WebMvcTest(CarController.class)
public class RESTAssureTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    void setup () {
      RestAssuredMockMvc.mockMvc(mvc);
    }
    
    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car audi = new Car("Audi", "A3");

        when(carManagerService.save(Mockito.any())).thenReturn(audi);

        String objectJson = new ObjectMapper().writeValueAsString(audi);
        
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(objectJson)
            .when()
            .post("/api/create")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("maker", equalTo("Audi"))
            .body("model", equalTo("A3"));

            verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car audi = new Car("Audi", "A3");
        Car bmw = new Car("BMW", "M3");

        List<Car> allCars = Arrays.asList(audi, bmw);

        when(carManagerService.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/cars")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$.size()", is(2))
            .body("[0].maker", is(audi.getMaker()))
            .body("[1].maker", is(bmw.getMaker()));
            
        verify(carManagerService, times(1)).getAllCars();
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJson() throws Exception {
        Car audi = new Car("Audi", "A3");

        when(carManagerService.getCarDetails(anyLong())).thenReturn(Optional.of(audi));

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/car/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("maker", is(audi.getMaker()));

        verify(carManagerService, times(1)).getCarDetails(anyLong());
    }
}
