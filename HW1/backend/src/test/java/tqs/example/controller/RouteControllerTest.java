package tqs.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import tqs.example.entity.Route;
import tqs.example.service.impl.RouteServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc 
class RouteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RouteServiceImpl routeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenGetRoutesByCriteria_thenReturnsRoutes() throws Exception {
        Route route1 = new Route(1, "Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50, "AA0000", null);
        Route route2 = new Route(2, "Porto", "Lisboa", "2021-05-01", "19:00", "21:00", 50, 100, 50, "AA0001", null);
        List<Route> allRoutes = Arrays.asList(route1, route2);

        when(routeService.getRoutesByOriginAndDestinationAndDate("Porto", "Lisboa", "2021-05-01"))
                .thenReturn(allRoutes);

        mvc.perform(get("/api/routes/routes")
                .param("origin", "Porto")
                .param("destination", "Lisboa")
                .param("date", "2021-05-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].origin", is("Porto")))
                .andExpect(jsonPath("$[1].origin", is("Porto")));

        verify(routeService, times(1)).getRoutesByOriginAndDestinationAndDate("Porto", "Lisboa", "2021-05-01");
    }

    @Test
    void whenGetRouteById_thenReturnsRoute() throws Exception {
        Route route = new Route(1, "Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50, "AA0000", null);

        when(routeService.getRouteById(1)).thenReturn(route);

        mvc.perform(get("/api/routes/route/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin", is("Porto")))
                .andExpect(jsonPath("$.destination", is("Lisboa")));

        verify(routeService, times(1)).getRouteById(1);
    }

}
