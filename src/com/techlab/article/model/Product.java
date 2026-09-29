package com.techlab.article.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product (String code, String name, double price, int stock, Category category) {
        this.code = validateString(code, "Código");
        this.name = validateString(name, "Nombre");
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        this.category = validateCategory (category);
    }

    private String validateString (String code, String message) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("El valor de '" + message + "' del producto no puede estar vacío");
        }
        return code;
    }

    private double validatePrice (double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe tener un valor mayor a cero");
        }
        return price;
    }

    private int validateStock (int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return stock;
    }

    private Category validateCategory (Category category) {
        if (category == null) {
            throw new IllegalArgumentException("El producto debe tener una categoría");
        }
        return category;
    }

    public void setCode (String code) {
        this.code = validateString(code, "Código");
    }

    public void setName (String name) {
            this.name = validateString(name, "Nombre");
    }

    public void setPrice (double price) {
        this.price = validatePrice(price);
    }

    public void setStock (int stock) {
        this.stock = validateStock (stock);
    }

    public void setCategory (Category category) {
        this.category = validateCategory(category);
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

    public String getProductType () {
        return "";
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
                " | Tipo: " + this.getProductType() +
                (this.category != null ? " | Categoría: " + this.category.getName() : "");
    }


}
