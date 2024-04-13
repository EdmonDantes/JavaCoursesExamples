package task14.factory;

import task14.entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {

    @Override
    public Product create() {
        return new Product(getNextId());
    }
}
