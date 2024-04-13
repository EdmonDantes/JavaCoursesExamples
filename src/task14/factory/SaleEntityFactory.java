package task14.factory;

import task14.entity.Sale;

public class SaleEntityFactory extends SimpleEntityFactory<Sale> {

    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
