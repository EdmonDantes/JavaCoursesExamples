package task12.factory;

import task12.entity.Sale;

public class SaleEntityFactory extends SimpleEntityFactory<Sale> {

    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
