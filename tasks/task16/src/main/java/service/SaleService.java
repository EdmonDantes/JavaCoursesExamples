package service;

import entity.Currency;
import entity.Sale;
import repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SaleService {

    private Repository<Sale> saleRepository;
    // 11.1
    private CurrencyService currencyService;

    // 11.2
    public SaleService(Repository<Sale> saleRepository, CurrencyService currencyService) {
        this.saleRepository = saleRepository;
        this.currencyService = currencyService;
    }

    public List<Sale> loadAllByPersonId(int personId) throws IOException {
        return saleRepository
                .loadAll()
                .stream()
                .filter(x -> x != null && x.getPerson() != null && x.getPerson().getId() == personId)
                .collect(Collectors.toList());
    }

    // 11.3
    public double getAmount(Sale sale, Currency currency) {
        if (sale == null || currency == null) {
            return 0;
        }

        return sale
                .getAmount()
                .entrySet()
                .stream()
                .mapToDouble(x -> currencyService.getRate(x.getKey(), currency) * x.getValue())
                .sum();
    }
}
