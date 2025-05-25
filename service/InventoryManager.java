package service;

import model.Product;

import java.io.*;
import java.util.*;

public class InventoryManager {
    private List<Product> productList;
    private static final String FILE_NAME = "data/products.dat";

    public InventoryManager() {
        productList = new ArrayList<>();
        loadFromFile();
    }

    public void addProduct(Product p) {
        productList.add(p);
        saveToFile();
    }

    public void removeProduct(String id) {
        productList.removeIf(p -> p.getId().equals(id));
        saveToFile();
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                productList = (List<Product>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Product findById(String id) {
        for (Product p : productList) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }
}
