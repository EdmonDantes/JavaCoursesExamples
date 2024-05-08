package repository;

import entity.Person;
import entity.Product;
import entity.Sale;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

// 4
public class DirectorySaleRepository implements Repository<Sale> {
    private final File dir;

    public DirectorySaleRepository(File dir) {
        this.dir = dir;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("DirectoryProductRepository required only directory");
        }
    }

    @Override
    public void save(Sale obj) throws IOException {
        File file = new File(dir.getPath() + "/" + obj.getId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(obj.getId());
            writer.println(obj.getAmount());
            writer.println(obj.getPerson().getId());
            writer.println(obj.getPerson().getName());
            writer.println(obj.getPerson().getAge());
            writer.println(obj.getProducts().size());
            for (Entry<Product, Double> productCount : obj.getProducts().entrySet()) {
                writer.println(productCount.getKey().getId());
                writer.println(productCount.getKey().getName());
                writer.println(productCount.getKey().getPrice());
                writer.println(productCount.getValue());
            }
        }
    }

    @Override
    public Sale load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();// skip id

            double amount = Double.parseDouble(scanner.nextLine());
            int personId = scanner.nextInt();
            scanner.nextLine();
            String personName = scanner.nextLine();
            int personAge = scanner.nextInt();
            scanner.nextLine();

            Person person = new Person(personId, personName, personAge);

            int productsSize = scanner.nextInt();
            scanner.nextLine();

            Map<Product, Double> products = new HashMap<>();
            for (int i = 0; i < productsSize; i++) {
                int productId = scanner.nextInt();
                scanner.nextLine();
                String productName = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                Product product = new Product(productId, productName, price);

                products.put(product, Double.parseDouble(scanner.nextLine()));
            }

            return new Sale(id, amount, person, products);
        }
    }

    @Override
    public List<Sale> load(List<Integer> ids) throws IOException {
        List<Sale> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }
}
