package task14.entity;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Sale implements Iterable<Product> {

    private int id;

    private double amount;

    private Person person;
    private Map<Product, Double> products;

    // 1
    private LocalDateTime timestamp;

    public Sale(int id) {
        this.id = id;
    }

    // 2
    public Sale(int id, double amount, Person person, Map<Product, Double> products, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.person = person;
        this.products = products;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }

    // 3
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("Sale{id=").append(id)
                .append(";amount=").append(amount)
                .append(";person={id=").append(person.getId())
                .append(";name=").append(person.getName())
                .append(";age=").append(person.getAge())
                .append("};timestamp=").append(timestamp)
                .append(";products={");

        for (Entry<Product, Double> productCount : products.entrySet()) {
            builder
                    .append(productCount.getKey().getName()).append('(').append(productCount.getKey().getId()).append(")=")
                    .append(productCount.getValue())
                    .append(";");
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.append("}}");


        return builder.toString();
    }
}
