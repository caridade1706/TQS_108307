package tqs.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.example.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    public Optional<Car> findByCarId(Long carId);

    public List<Car> findAll();

}

