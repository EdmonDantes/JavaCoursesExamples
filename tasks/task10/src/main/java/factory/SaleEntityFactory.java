package factory;

import entity.Sale;

 // 9
public class SaleEntityFactory extends SimpleEntityFactory<Sale>{
    // 9.1
    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
