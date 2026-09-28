package com.techlab.article.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product (String code, String name, double price, int stock, Category category) {
        setCode (code);
        setName (name);
        setPrice (price);
        setStock (stock);
        setCategory (category);
    }

    public final void setCode (String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        this.code = code;
    }

    public final void setName (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        this.name = name;
    }

    public final void setPrice (double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe tener un valor mayor a cero");
        }
        this.price = price;
    }

    public final void setStock (int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }

    public final void setCategory (Category category) {
        if (category == null) {
            throw new IllegalArgumentException("El producto debe tener una categoría");
        }
        this.category = category;
    }

    public String getCode () {
        return this.code;
    }

    public String getName () {
        return this.name;
    }

    public double getPrice () {
        return this.price;
    }

    public int getStock () {
        return this.stock;
    }

    public Category getCategory () {
        return this.category;
    }

    public String getSpecificDetail () {
        return "";
    }

    @Override
    public String toString () {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Precio: $ " + this.price +
                " | Stock: " + this.stock +
                " | Detalle: " + this.getSpecificDetail() +
                (this.category != null ? " | Categoría: " + this.category.getName() : "");
    }


}
