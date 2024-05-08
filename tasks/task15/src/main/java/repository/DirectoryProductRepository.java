package repository;

import entity.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        return loadFromFile(file);
    }

    @Override
    public List<Product> load(List<Integer> ids) throws IOException {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }

    // 9
    @Override
    public List<Product> loadAll() throws IOException {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<Product> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(loadFromFile(file));
        }
        return list;
    }

    private Product loadFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            int id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            return new Product(id, name, price);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
