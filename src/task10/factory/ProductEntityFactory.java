package task10.factory;

import task10.entity.Product;

// 8
public class ProductEntityFactory extends SimpleEntityFactory<Product>{

    // 8.1
    @Override
    public Product create() {
        return new Product(getNextId());
    }
}
