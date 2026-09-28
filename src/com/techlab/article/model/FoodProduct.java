package com.techlab.article.model;

import java.time.LocalDate;

public class FoodProduct extends Product{
    private LocalDate expirationDate;

    public FoodProduct (String code, String name, double price, int stock, Category category, LocalDate expirationDate) {
        super(code, name, price, stock, category);
        setExpirationDate (expirationDate);
    }

    public final void setExpirationDate (LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula");
        }
        if (expirationDate.isBefore(LocalDate.of(1999, 12, 31)) || expirationDate.isAfter(LocalDate.of(2100, 1, 1))) {
            throw new IllegalArgumentException("Fecha de vencimiento fuera de rango permitido");
        }
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate () {
        return this.expirationDate;
    }

    @Override
    public String getProductType () {
        return "Producto alimenticio";
    }

    @Override
    public String getSpecificDetail () {
        return "Fecha de vencimiento: " + this.expirationDate;
    }

}
