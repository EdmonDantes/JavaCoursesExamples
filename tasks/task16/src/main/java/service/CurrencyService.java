package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;
import org.apache.commons.lang3.StringUtils;
import repository.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 10
public class CurrencyService {

    private Repository<Currency> currencyRepository;
    private Map<Currency, Map<Currency, Double>> rates = new HashMap<>();

    // 10.1
    public CurrencyService(ObjectMapper mapper, Repository<Currency> currencyRepository) throws IOException {
        URL url = CurrencyService.class.getResource("/currencies_rates.json");
        Objects.requireNonNull(url, "Can not file 'currencies_rates.json' in resources");

        this.currencyRepository = currencyRepository;
        Map<Integer, Map<Integer, Double>> rates = mapper.readValue(url, new TypeReference<>() {
        });

        rates.forEach((fromId, curPair) -> {
            Currency from;
            try {
                from = currencyRepository.load(fromId);
            } catch (IOException ignore) {
                return;
            }

            Map<Currency, Double> curPairRate = this.rates.computeIfAbsent(from, x -> new HashMap<>());
            curPair.forEach((toId, rate) -> {
                Currency to;
                try {
                    to = currencyRepository.load(toId);
                } catch (IOException ignore) {
                    return;
                }

                curPairRate.put(to, rate);
            });
        });
    }

    // 10.2
    public Currency getById(int id) {
        try {
            return currencyRepository.load(id);
        } catch (Exception e) {
            return null;
        }
    }

    // 10.3
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

    // 10.4
    public double getRate(Currency from, Currency to) {
        Map<Currency, Double> currencyPair = rates.get(from);
        Double rate = currencyPair == null ? null : currencyPair.get(to);
        return rate == null ? -1.0 : rate;
    }

}
