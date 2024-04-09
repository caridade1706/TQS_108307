package tqs.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tqs.example.entity.Route;
import tqs.example.repository.RouteRepository;
import tqs.example.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route getRouteById(int routeid) {
        return routeRepository.findByRouteId(routeid);
    }

    @Override
    public boolean isfull(Route route) {
        return route.getOcupation() == 0;
    }

    @Override
    public List<Route> getRoutesByOriginAndDestinationAndDate(String origin, String destination, String date) {
        return routeRepository.getRoutesByOriginAndDestinationAndDate(origin, destination, date);
    }


}