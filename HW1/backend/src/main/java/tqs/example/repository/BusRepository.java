package tqs.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.example.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, String>{

    Bus findByPlate(String plate);
    List<Bus> findByCompany(String company);
    
}