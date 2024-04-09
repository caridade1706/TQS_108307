package tqs.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import tqs.example.entity.Route;
import tqs.example.service.RouteService;

import org.springframework.http.ResponseEntity;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController
@AllArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;
    
    private static Logger logger = LogManager.getLogger(RouteController.class);


    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getRoutesByOriginAndDestinationAndDate(
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "destination", required = false) String destination,
            @RequestParam(value = "date", required = false) String date) {
        List<Route> routes = routeService.getRoutesByOriginAndDestinationAndDate(origin, destination, date);
        logger.info("Getting all routes");
        logger.info("Routes: {}",routes);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") int id) {
        Route route = routeService.getRouteById(id);
        logger.info("Getting route by id");
        logger.info("Route: {}",route);
        return ResponseEntity.ok(route);
    }

}
