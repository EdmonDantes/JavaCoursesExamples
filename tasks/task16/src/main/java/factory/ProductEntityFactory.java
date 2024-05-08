package factory;

import entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {

    @Override
    public Product create() {
        return new Product(getNextId());
    }
}
