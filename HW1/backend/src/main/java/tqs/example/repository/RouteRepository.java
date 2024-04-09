package tqs.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.example.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Route findByRouteId(int id);
    List<Route> getRoutesByOriginAndDestinationAndDate(String origin, String destination, String date);
    boolean existsByOriginAndDestinationAndDate(String origin, String destination, String date);
}
