package task12.factory;

import task12.entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {

    @Override
    public Product create() {
        return new Product(getNextId());
    }
}
