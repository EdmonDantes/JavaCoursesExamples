package task10.entity;

// 1
public class Product implements Comparable<Product> {

    // 1.1
    private int id;

    // 1.2
    private String name;

    // 1.3
    private double price;

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

    // 1.4
    @Override
    public int compareTo(Product another) {
        return Integer.compare(id, another.id);
    }
}
