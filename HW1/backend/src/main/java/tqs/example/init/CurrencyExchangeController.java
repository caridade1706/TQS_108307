package tqs.example.init;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
public class CurrencyExchangeController {

    private static Logger logger = LogManager.getLogger(CurrencyExchangeController.class);

    private final ExchangeRateCache exchangeRateCache;
    private final String apiKey;

    public CurrencyExchangeController(ExchangeRateCache exchangeRateCache, 
    @Value("${api.key}") String apiKey) {
        this.exchangeRateCache = exchangeRateCache;
        this.apiKey = apiKey;
    }

    @GetMapping("/api/exchange-rate/all")
    public Map<String, Map<String, Double>> getAllExchangeRates() {
        Map<String, Map<String, Double>> cachedRates = exchangeRateCache.getAllRates();
        if (cachedRates != null && !cachedRates.isEmpty()) {
            logger.info("Getting all exchange rates from cache");
            return cachedRates;
        }

        // Usa a apiKey injetada
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/EUR", apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        
        JSONObject jsonResponse = new JSONObject(response);
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

        Map<String, Map<String, Double>> allRates = new HashMap<>();
        conversionRates.keys().forEachRemaining(baseCurrency -> {
            Map<String, Double> rates = new HashMap<>();
            conversionRates.keys().forEachRemaining(toCurrency -> 
                rates.put(toCurrency, conversionRates.getDouble(toCurrency))
            );
            allRates.put(baseCurrency, rates);
        });

        // Atualiza o cache com as novas taxas
        exchangeRateCache.updateAllRates(allRates);
        
        logger.info("Getting all exchange rates from API");
        return allRates;
    }
}
