package service;

import entity.Product;
import repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private Repository<Product> productRepository;

    public ProductService(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> loadAllByMaxPrice(double maxPrice) throws IOException {
        return productRepository
                .loadAll()
                .stream()
                .filter(x -> x != null && x.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }
}
