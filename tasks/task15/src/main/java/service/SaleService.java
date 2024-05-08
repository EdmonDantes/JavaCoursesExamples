package service;

import entity.Sale;
import repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// 13
public class SaleService {

    private Repository<Sale> saleRepository;

    public SaleService(Repository<Sale> saleRepository) {
        this.saleRepository = saleRepository;
    }

    // 14
    public List<Sale> loadAllByPersonId(int personId) throws IOException {
        return saleRepository
                .loadAll()
                .stream()
                .filter(x -> x != null && x.getPerson() != null && x.getPerson().getId() == personId)
                .collect(Collectors.toList());
    }
}
