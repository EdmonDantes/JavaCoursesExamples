package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;
import org.apache.commons.lang3.StringUtils;
import repository.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyService {

    private static final String RATES_REQUEST_URL = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/%s.min.json";

    // 8.1
    private HttpClient client;
    // 8.2
    private ObjectMapper mapper;
    private Repository<Currency> currencyRepository;
    // 8.3

    // 8.4
    public CurrencyService(HttpClient client, ObjectMapper mapper, Repository<Currency> currencyRepository) {
        this.client = client;
        this.mapper = mapper;
        this.currencyRepository = currencyRepository;
    }

    public Currency getById(int id) {
        try {
            return currencyRepository.load(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Currency getByISO(String isoName) {
        if (StringUtils.isNotBlank(isoName)) {
            try {
                return currencyRepository
                        .loadAll()
                        .stream()
                        .filter(x -> x.getIsoName().equals(isoName))
                        .findFirst()
                        .orElse(null);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    // 8.5
    public double getRate(Currency from, Currency to) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(String.format(RATES_REQUEST_URL, from.getIsoName().toLowerCase())))
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() != 200) {
                return -1;
            }

            JsonNode json = mapper.readValue(response.body(), JsonNode.class);
            JsonNode rates = json.get(from.getIsoName().toLowerCase());
            if (rates == null) {
                return -1;
            }

            JsonNode rate = rates.get(to.getIsoName().toLowerCase());
            if (rate == null) {
                return -1;
            }

            return rate.asDouble(-1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
