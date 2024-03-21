package tqs.example.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.example.Entity.Car;
import tqs.example.Service.CarManagerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    
    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.carManagerService.save(car));
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return this.carManagerService.getAllCars();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "carId") Long carId){
        Optional<Car> car = this.carManagerService.getCarDetails(carId);
        if (car.isPresent()) {
            return ResponseEntity.ok().body(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
