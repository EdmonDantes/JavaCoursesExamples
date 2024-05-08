package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

// 6
public class CurrencyResourceRepository extends MemoryRepository<Currency> {

    // 6.1
    public CurrencyResourceRepository(ObjectMapper mapper) throws IOException {
        URL url = Currency.class.getResource("/currencies.json");
        Objects.requireNonNull(url, "Can not file 'currencies.json' in resources");
        List<Currency> currencies = mapper.readValue(url, new TypeReference<>() {
        });

        for (Currency currency : currencies) {
            if (storage.put(currency.getId(), currency) != null) {
                throw new IllegalStateException("Currency with id " + currency.getId() + " already exists");
            }
        }
    }

    // 6.2
    @Override
    public void save(Currency obj) throws IOException {
        throw new UnsupportedOperationException("Not supported saving for CurrencyResourceRepository");
    }

    @Override
    protected int getId(Currency obj) {
        return obj.getId();
    }
}
