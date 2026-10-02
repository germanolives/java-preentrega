package com.techlab.article.model;

public abstract class Product {
    private String code;
    private String name;
    private double price;
    private Category category;

    public Product(String code, String name, double price, Category category) {
        this.code = validateString(code, "Código");
        this.name = validateString(name, "Nombre");
        this.price = validatePrice(price);
        this.category = validateCategory(category);
    }

    private String validateString(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("El valor de '" + message + "' del producto no puede estar vacío");
        }
        return string.trim();
    }

    private double validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe tener un valor mayor a cero");
        }
        return price;
    }

    private Category validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("El producto debe tener una categoría");
        }
        return category;
    }

    public void setCode(String code) {
        this.code = validateString(code, "Código");
    }

    public void setName(String name) {
        this.name = validateString(name, "Nombre");
    }

    public void setPrice(double price) {
        this.price = validatePrice(price);
    }

    public void setCategory(Category category) {
        this.category = validateCategory(category);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public abstract ProductType getProductType();

    public abstract String getSpecificDetail();

    @Override
    public String toString() {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Precio: $ " + this.price +
                " | Detalle: " + this.getSpecificDetail() +
                " | Tipo: " + this.getProductType() +
                (this.category != null ? " | Categoría: " + this.category.getName() : "");
    }

}
