package task12.entity;

import java.util.Iterator;
import java.util.Map;

public class Sale implements Iterable<Product> {

    private int id;

    private double amount;

    private Person person;
    private Map<Product, Double> products;

    public Sale(int id) {
        this.id = id;
    }

    public Sale(int id, double amount, Person person, Map<Product, Double> products) {
        this.id = id;
        this.amount = amount;
        this.person = person;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Map<Product, Double> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Double> products) {
        this.products = products;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
