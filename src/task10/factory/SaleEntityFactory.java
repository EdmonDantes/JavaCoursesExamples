package task10.factory;

import task10.entity.Sale;

 // 9
public class SaleEntityFactory extends SimpleEntityFactory<Sale>{
    // 9.1
    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
