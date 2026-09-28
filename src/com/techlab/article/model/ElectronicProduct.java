package com.techlab.article.model;

public class ElectronicProduct extends Product {
    private int monthsOfWarranty;

    public ElectronicProduct (String code, String name, double price, int stock, Category category, int monthsOfWarranty) {
        super(code, name, price, stock, category);
        setWarranty (monthsOfWarranty);
    }

    public final void setWarranty (int monthsOfWarranty) {
        if (monthsOfWarranty < 0) {
            throw new IllegalArgumentException("La garantía debe ser superior a un mes");
        }
        this.monthsOfWarranty = monthsOfWarranty;
    }

    public int getMonthsOfWarranty () {
        return this.monthsOfWarranty;
    }

    @Override
    public String getSpecificDetail () {
        return "Garantía: " + this.monthsOfWarranty + " meses";
    }
}
