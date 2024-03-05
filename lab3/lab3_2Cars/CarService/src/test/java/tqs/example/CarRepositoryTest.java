package tqs.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.example.Entity.Car;
import tqs.example.Repository.CarRepository;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {}

    @Test
    public void whenFindById_thenReturnCar() {
        Car audi = new Car("Audi", "A3");
        entityManager.persistAndFlush(audi);

        Car found = carRepository.findByCarId(audi.getCarId()).orElse(null);

        assertThat(found).isEqualTo(audi);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car car = carRepository.findByCarId(-11L).orElse(null);
        assertThat(car).isNull();

    }

    @Test
    public void testFindAll() {
        Car audi = new Car("Audi", "A3");
        Car bmw = new Car("BMW", "M3");
        Car mercedes = new Car("Mercedes", "CLA");

        entityManager.persist(audi);
        entityManager.persist(bmw);
        entityManager.persist(mercedes);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars.size()).isEqualTo(3);
    }
    
}
