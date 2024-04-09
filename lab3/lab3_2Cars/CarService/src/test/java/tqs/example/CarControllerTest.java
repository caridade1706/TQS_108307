package tqs.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tqs.example.Entity.Car;
import tqs.example.Service.CarManagerService;
import tqs.example.Controller.CarController;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car audi = new Car("Audi", "A3");

        when(carManagerService.save(Mockito.any())).thenReturn(audi);

        String objectJson = new ObjectMapper().writeValueAsString(audi);

        mvc.perform(
                post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Audi")))
                .andExpect(jsonPath("$.model", is("A3")));

        verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car audi = new Car("Audi", "A3");
        Car bmw = new Car("BMW", "M3");

        List<Car> allCars = Arrays.asList(audi, bmw);

        when(carManagerService.getAllCars()).thenReturn(allCars);

        mvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maker", is(audi.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(bmw.getMaker())));

        verify(carManagerService, times(1)).getAllCars();
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJson() throws Exception {
        Car audi = new Car("Audi", "A3");

        when(carManagerService.getCarDetails(anyLong())).thenReturn(Optional.of(audi));

        mvc.perform(get("/api/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(audi.getMaker())));

        verify(carManagerService, times(1)).getCarDetails(anyLong());
    }

    
}
