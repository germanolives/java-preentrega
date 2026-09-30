package com.techlab.article.model;

public class ElectronicProduct extends Product {
    private int monthsOfWarranty;

    public ElectronicProduct(String code, String name, double price, int stock, Category category,
            int monthsOfWarranty) {
        super(code, name, price, stock, category);
        this.monthsOfWarranty = validateMonthsOfWarranty(monthsOfWarranty);
    }

    private int validateMonthsOfWarranty(int monthsOfWarranty) {
        if (monthsOfWarranty < 0) {
            throw new IllegalArgumentException("La garantía no puede tener un valor menor a cero");
        }
        return monthsOfWarranty;
    }

    public void setWarranty(int monthsOfWarranty) {
        this.monthsOfWarranty = validateMonthsOfWarranty(monthsOfWarranty);
    }

    public int getMonthsOfWarranty() {
        return this.monthsOfWarranty;
    }

    public String helpDeskPhoneNumber() {
        return "+54 11 1234-5678";
    }

    @Override
    public String getProductType() {
        return "Producto electrónico";
    }

    @Override
    public String getSpecificDetail() {
        return "Garantía: " + this.monthsOfWarranty + " meses";
    }
}
