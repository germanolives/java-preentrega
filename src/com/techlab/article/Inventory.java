package com.techlab.article;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Inventory {
    private final List<Product> products;

    public Inventory () {
        this.products = new ArrayList<>();
    }

    public boolean isEmpty () {
        return (this.products.isEmpty());
    }

    public void addProductToInventory (Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto debe existir");
        }
        if (this.isCodeIntoInventory(product.getCode())) {
            throw new IllegalArgumentException("El producto ya existe");
        }
        this.products.add(product);
    }

    public void removeProductFromInventory (String code) {
        if (code != null && !code.isBlank() && !this.products.isEmpty()) {
            this.products.removeIf(item -> item.getCode().equalsIgnoreCase(code.trim()));
        }
    }

    public Product getProductByCode (String code) {
        if (code == null || code.isBlank() || this.products.isEmpty()) return null;
        return this.products.stream()
                .filter(item -> item.getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(null);
    }

    public boolean isCodeIntoInventory (String code) {
        return this.getProductByCode(code) != null;
    }

    public Product getProductByName (String name) {
        if (name == null || name.isBlank() || this.products.isEmpty()) return null;
        return this.products.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);
    }

    public int getIndexByName (String name) {
        if (name == null || name.isBlank() || this.products.isEmpty()) return -1;
        return IntStream.range(0, this.products.size())
                .filter(item -> this.products.get(item).getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(-1);
    }

    public int getIndexByCode (String code) {
        if (code == null || code.isBlank() || this.products.isEmpty()) return -1;
        return IntStream.range(0, this.products.size())
                .filter(item -> this.products.get(item).getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(-1);
    }

    public List<Product> getProducts () {
        return Collections.unmodifiableList(this.products);
    }
}
