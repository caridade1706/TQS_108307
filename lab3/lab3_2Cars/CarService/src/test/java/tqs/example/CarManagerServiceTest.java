package tqs.example;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.example.Entity.Car;
import tqs.example.Repository.CarRepository;
import tqs.example.Service.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car audi = new Car("Audi", "A3");
        Car bmw = new Car("BMW", "M3");
        Car mercedes = new Car("Mercedes", "CLA");

        List<Car> allCars = Arrays.asList(audi, bmw, mercedes);
        
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(audi.getCarId())).thenReturn(Optional.of(audi));
        Mockito.when(carRepository.findByCarId(bmw.getCarId())).thenReturn(Optional.of(bmw));
        Mockito.when(carRepository.findByCarId(mercedes.getCarId())).thenReturn(Optional.of(mercedes));
        Mockito.when(carRepository.findByCarId(0L)).thenReturn(Optional.empty());
    }

    @Test
    public void testAllCars() {
        List<Car> allCars = carManagerService.getAllCars();
       
        assertThat(allCars.size()).isEqualTo(3);
        assertThat(allCars.get(0).getMaker()).isEqualTo("Audi");
        assertThat(allCars.get(1).getMaker()).isEqualTo("BMW");
        assertThat(allCars.get(2).getMaker()).isEqualTo("Mercedes");

        verify(carRepository, times(1)).findAll();

    }

    @Test
    public void testDoesntExist() {

        assertThat(carManagerService.getCarDetails(0L)).isEmpty();
        verify(carRepository, times(1)).findByCarId(0L);
    }

    @Test
    public void saveCar() {
        Car audi = new Car("Audi", "A5");
        Mockito.when(carRepository.save(audi)).thenReturn(audi);

        assertThat(carManagerService.save(audi)).isEqualTo(audi);
        verify(carRepository, times(1)).save(audi);
    }

    
}
