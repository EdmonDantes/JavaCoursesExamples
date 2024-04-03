package task10.repository;

import task10.entity.Product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 3
public class DirectoryProductRepository implements Repository<Product> {
    private final File dir;

    public DirectoryProductRepository(File dir) {
        this.dir = dir;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("DirectoryProductRepository required only directory");
        }
    }

    @Override
    public void save(Product obj) throws IOException {
        File file = new File(dir.getPath() + "/" + obj.getId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(obj.getId());
            writer.println(obj.getName());
            writer.println(obj.getPrice());
        }
    }

    @Override
    public Product load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            String name = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            return new Product(id, name, price);
        }
    }

    @Override
    public List<Product> load(List<Integer> ids) throws IOException {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }
}
