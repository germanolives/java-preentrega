package com.techlab.article.model;

public class Category {
    private String code;
    private String name;
    private String description;
    private ProductType productType;

    public Category(String code, String name, String description, ProductType productType) {
        this.code = validateString(code, "Código");
        this.name = validateString(name, "Nombre");
        this.description = validateString(description, "Descripción");
        this.productType = validateProductType(productType);
    }

    private String validateString(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("El valor de '" + message + "' de la categoría no puede estar vacío");
        }
        return string.trim();
    }

    private ProductType validateProductType (ProductType productType) {
        if (productType == null) {
            throw new IllegalArgumentException("El tipo de producto debe existir");
        }
        return productType;
    }

    public void setCode(String code) {
        this.code = validateString(code, "Código");
    }

    public void setName(String name) {
        this.name = validateString(name, "Nombre");
    }

    public void setDescription(String description) {
        this.description = validateString(description, "Descripción");
    }

    public void setProductType (ProductType productType) {
        this.productType = validateProductType(productType);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductType getProductType () {
        return this.productType;
    }

    @Override
    public String toString() {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Descripción: " + this.description +
                " | Tipo de producto: " + this.productType.getDescription();
    }
}
