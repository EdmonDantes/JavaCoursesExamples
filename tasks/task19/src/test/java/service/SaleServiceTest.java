package service;

import entity.Person;
import entity.Sale;
import factory.EntityFactory;
import factory.SaleEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.MemoryRepositoryByLambda;
import repository.Repository;

import java.io.IOException;
import java.util.List;

// 16
public class SaleServiceTest {

    private static final int PERSON_ID = 123;
    private static final int COUNT = 7;

    private final EntityFactory<Sale> factory;
    private final Repository<Sale> repository;
    private final SaleService saleService;

    public SaleServiceTest() throws IOException {
        factory = new SaleEntityFactory();
        repository = new MemoryRepositoryByLambda<Sale>(Sale::getId);
        saleService = new SaleService(repository, null);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            Sale sale = factory.create();

            sale.setPerson(new Person(count++ < COUNT ? PERSON_ID : count));

            repository.save(sale);
        }
    }

    @Test
    public void testPositive() throws IOException {
        List<Sale> sales = saleService.loadAllByPersonId(PERSON_ID);
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(COUNT, sales.size());
    }

    @Test
    public void testNegative() throws IOException {
        List<Sale> sales = saleService.loadAllByPersonId(0);
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(0, sales.size());
    }

}
