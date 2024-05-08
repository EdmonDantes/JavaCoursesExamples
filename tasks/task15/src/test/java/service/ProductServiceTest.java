package service;

import entity.Product;
import factory.EntityFactory;
import factory.ProductEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.MemoryRepositoryByLambda;
import repository.Repository;

import java.io.IOException;
import java.util.List;

// 15
public class ProductServiceTest {

    private static final double MAX_PRICE = 56.70;

    private final EntityFactory<Product> factory;
    private final Repository<Product> repository;
    private final ProductService productService;

    public ProductServiceTest() throws IOException {
        factory = new ProductEntityFactory();
        repository = new MemoryRepositoryByLambda<Product>(Product::getId);
        productService = new ProductService(repository);

        for (int i = 0; i < 10; i++) {
            Product product = factory.create();

            product.setName("Product " + i);
            product.setPrice(i * 10);

            repository.save(product);
        }
    }

    @Test
    public void testPositive() throws IOException {
        List<Product> products = productService.loadAllByMaxPrice(MAX_PRICE);
        Assertions.assertNotNull(products);
        Assertions.assertEquals(6, products.size());
    }

    @Test
    public void testNegative() throws IOException {
        List<Product> products = productService.loadAllByMaxPrice(0);
        Assertions.assertNotNull(products);
        Assertions.assertEquals(0, products.size());
    }

}
