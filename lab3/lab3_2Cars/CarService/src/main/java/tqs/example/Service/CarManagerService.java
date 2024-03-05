package tqs.example.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.example.Repository.CarRepository;
import tqs.example.Entity.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return this.carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId){
        return this.carRepository.findByCarId(carId);
    }

}
