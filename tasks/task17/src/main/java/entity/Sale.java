package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Sale implements Iterable<Product> {

    private int id;
    private Person person;
    private Map<Product, Double> products;

    private LocalDateTime timestamp;

    // 5.1
    public Sale() {
    }

    public Sale(int id) {
        this.id = id;
    }

    public Sale(int id, Person person, Map<Product, Double> products, LocalDateTime timestamp) {
        this.id = id;
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

    @JsonIgnore
    public Map<Currency, Double> getAmount() {
        Map<Currency, Double> result = new HashMap<>();
        products.forEach((product, count) -> {
            double productAmount = product.getPrice() * count;
            result.compute(product.getCurrency(), (currency, sum) -> sum == null ? productAmount : sum + productAmount);
        });

        return result;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // 5.2
    @JsonIgnore
    public Map<Product, Double> getProducts() {
        return products;
    }

    // 5.6
    @JsonProperty("products")
    public List<JsonProduct> getJsonProducts() {
        return products.entrySet().stream().map(x -> new JsonProduct(x.getKey(), x.getValue())).collect(Collectors.toList());
    }

    // 5.7
    @JsonProperty("products")
    public void setJsonProducts(List<JsonProduct> jsonProducts) {
        products = new HashMap<>();
        for (JsonProduct jsonProduct : jsonProducts) {
            products.put(jsonProduct.getProduct(), jsonProduct.getCount());
        }
    }

    // 5.3
    @JsonIgnore
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("Sale{id=").append(id)
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


    // 5.4, 5.5
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class JsonProduct {
        private Product product;
        private Double count;
    }
}
