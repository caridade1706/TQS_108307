package tqs.example.init;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExchangeRateCache {
    // Mapa para armazenar a taxa de câmbio de todas as moedas
    private final Map<String, Map<String, Double>> allRates = new ConcurrentHashMap<>();
    // Última atualização do cache
    private LocalDateTime lastUpdate = LocalDateTime.MIN;
    private static final long TTL_MINUTES = 15;

    public Map<String, Map<String, Double>> getAllRates() {
        LocalDateTime now = LocalDateTime.now();
        if (lastUpdate.plusMinutes(TTL_MINUTES).isAfter(now)) {
            return allRates;
        }
        return new ConcurrentHashMap<>();
    }

    public void updateAllRates(Map<String, Map<String, Double>> rates) {
        allRates.clear();
        allRates.putAll(rates);
        lastUpdate = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
