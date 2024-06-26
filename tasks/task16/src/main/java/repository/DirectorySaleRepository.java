package repository;

import entity.Person;
import entity.Product;
import entity.Sale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class DirectorySaleRepository implements Repository<Sale> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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
            writer.println(DATE_TIME_FORMATTER.format(obj.getTimestamp()));
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
        return loadFromFile(file);
    }

    @Override
    public List<Sale> load(List<Integer> ids) throws IOException {
        List<Sale> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }

    @Override
    public List<Sale> loadAll() throws IOException {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<Sale> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(loadFromFile(file));
        }
        return list;
    }

    private Sale loadFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            int id = Integer.parseInt(scanner.nextLine());

            LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), DATE_TIME_FORMATTER);
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

            return new Sale(id, person, products, timestamp);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
