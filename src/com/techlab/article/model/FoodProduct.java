package com.techlab.article.model;

import com.techlab.article.util.ValidateUtil;

import java.time.LocalDate;

public class FoodProduct extends Product {
    private LocalDate expirationDate;

    public FoodProduct(String code, String name, double price, Category category, LocalDate expirationDate) {
        super(code, name, price, category);
        if (category.getProductType() != ProductType.FOOD) {
            throw new IllegalArgumentException(
                    "La categoría debe ser tipo de producto '" + ProductType.FOOD.getDescription() + "'");
        }
        this.expirationDate = ValidateUtil.expirationDate(expirationDate);
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = ValidateUtil.expirationDate(expirationDate);
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
        return "Fecha de vencimiento: " + getExpirationDate();
    }

}
