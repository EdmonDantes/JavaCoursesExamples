package factory;

import entity.Product;

// 8
public class ProductEntityFactory extends SimpleEntityFactory<Product> {

    // 8.1
    @Override
    public Product create() {
        return new Product(getNextId());
    }
}
