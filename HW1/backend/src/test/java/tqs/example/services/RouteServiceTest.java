package tqs.example.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Arrays;

import tqs.example.entity.Bus;
import tqs.example.entity.Route;
import tqs.example.repository.RouteRepository;
import tqs.example.service.impl.RouteServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock(lenient = true)
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    private Route route;
    private Route route1;
    private Route route2;

    @BeforeEach
    void setUp() {
        Bus bus = new Bus("AA0000", 50, "TST", "Mercedes", 2021);
        route = new Route(0, "Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50, "AA0000", bus);
        route1 = new Route(1, "Porto", "Lisboa", "2021-05-01", "19:00", "21:00", 50, 100, 50, "AA0000", bus);
        route2 = new Route(2, "Coimbra", "Lisboa", "2021-05-03", "12:00", "14:00", 50, 100, 0, "AA0000", bus);

        Mockito.when(routeRepository.findByRouteId(route.getId())).thenReturn(route);
        Mockito.when(routeRepository.getRoutesByOriginAndDestinationAndDate("Porto", "Lisboa", "2021-05-01"))
                .thenReturn(Arrays.asList(route, route1));
    }

    @Test
    void whenGetRouteById_thenRouteShouldBeFound() {
        Route found = routeServiceImpl.getRouteById(route.getId());
        assertThat(found).isNotNull();
        assertEquals("Porto", found.getOrigin());
        verify(routeRepository, times(1)).findByRouteId(route.getId());
    }

    @Test
    void whenGetRoutesByOriginAndDestinationAndDate_thenRoutesShouldBeFound() {
        List<Route> found = routeServiceImpl.getRoutesByOriginAndDestinationAndDate("Porto", "Lisboa", "2021-05-01");
        assertThat(found)
                .isNotNull()
                .hasSize(2);
        verify(routeRepository, times(1)).getRoutesByOriginAndDestinationAndDate("Porto", "Lisboa", "2021-05-01");
    }

    @Test
    void isfullTest() {
        assertThat(routeServiceImpl.isfull(route)).isFalse();
        assertThat(routeServiceImpl.isfull(route2)).isTrue();
    }

}
