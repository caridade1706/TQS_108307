package tqs.example.init;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExchangeRateCacheTest {

    private ExchangeRateCache cache;
    private Map<String, Map<String, Double>> rates;
    private MockedStatic<LocalDateTime> mockedLocalDateTime;

    @AfterEach
    void tearDown() {
        mockedLocalDateTime.close();
    }

    @BeforeEach
    void setUp() {
        cache = new ExchangeRateCache();
        rates = new HashMap<>();
        Map<String, Double> usdRates = new HashMap<>();
        usdRates.put("EUR", 0.85);
        usdRates.put("GBP", 0.75);
        rates.put("USD", usdRates);

        mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class);
        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(LocalDateTime.MIN);
    }

    @Test
    void testUpdateAndGetRates() {
        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(LocalDateTime.MIN);

        cache.updateAllRates(rates);
        assertEquals(rates, cache.getAllRates(), "Rates should be updated correctly.");
        assertEquals(LocalDateTime.MIN, cache.getLastUpdate(), "Last update should match mocked time.");
    }

    @Test
    void testGetRatesAfterTTLExpiration() {
        cache.updateAllRates(rates);

        // Simulate time after TTL expiration
        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(LocalDateTime.MIN.plusHours(1));

        assertThat(cache.getAllRates()).isEmpty();
    }
}
