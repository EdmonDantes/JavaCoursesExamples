package entity;

public class Product implements Comparable<Product> {

    private int id;

    private String name;

    private double price;

    // 7
    private Currency currency;

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int compareTo(Product another) {
        return Integer.compare(id, another.id);
    }
}
