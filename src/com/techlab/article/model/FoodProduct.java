package com.techlab.article.model;

import java.time.LocalDate;

public class FoodProduct extends Product {
    private LocalDate expirationDate;

    public FoodProduct(String code, String name, double price, Category category, LocalDate expirationDate) {
        super(code, name, price, category);
        if (category.getProductType() != ProductType.FOOD) {
            throw new IllegalArgumentException("La categoría debe ser tipo de producto '" + ProductType.FOOD.getDescription() + "'");
        }
        this.expirationDate = validateExpirationDate(expirationDate);
    }

    private LocalDate validateExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula");
        }
        if (expirationDate.isBefore(LocalDate.of(1999, 12, 31)) || expirationDate.isAfter(LocalDate.of(2100, 1, 1))) {
            throw new IllegalArgumentException("Fecha de vencimiento fuera de rango permitido");
        }
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = validateExpirationDate(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.FOOD;
    }

    @Override
    public String getSpecificDetail() {
        return "Fecha de vencimiento: " + this.expirationDate;
    }

}
