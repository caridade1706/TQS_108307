package tqs.example.service;

import java.util.List;

import tqs.example.entity.Route;

public interface RouteService {

    Route getRouteById(int routeid);
    boolean isfull(Route routeById);
    List<Route> getRoutesByOriginAndDestinationAndDate(String origin, String destination, String date);

}