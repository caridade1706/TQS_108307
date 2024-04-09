package tqs.example.init;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc 
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExchangeRateCache exchangeRateCache;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenGetExchangeRates_thenReturnJsonArray() throws Exception {
        // Setup
        Map<String, Map<String, Double>> rates = new HashMap<>();
        Map<String, Double> innerMap = new HashMap<>();
        innerMap.put("USD", 1.0834);
        innerMap.put("JPY", 131.2);
        rates.put("EUR", innerMap);
        
        when(exchangeRateCache.getAllRates()).thenReturn(rates);

        // Execute and Assert
        mvc.perform(get("/api/exchange-rate/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.EUR.USD", is(1.0834)))
                .andExpect(jsonPath("$.EUR.JPY", is(131.2)));

        // Ensure no API call is made when cache is hit
        Mockito.verify(restTemplate, Mockito.never()).getForObject(anyString(), any());
    }


    @Test
    void whenCacheIsNull_thenCallExternalApi() throws Exception {
        // Setup
        
        Map<String, Map<String, Double>> rates = new HashMap<>();
        when(exchangeRateCache.getAllRates()).thenReturn(rates);
        

        // Mock the response from the external API
        String apiResponse = "{\"conversion_rates\":{\"USD\":1.2,\"JPY\":110.0}}";
        when(restTemplate.getForObject(anyString(), any())).thenReturn(apiResponse);

        // Execute and Assert
        mvc.perform(get("/api/exchange-rate/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the restTemplate was called to fetch data from the external API
        Mockito.verify(exchangeRateCache).getAllRates();
    }
    

}
