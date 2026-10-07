package com.techlab.article.util;

import com.techlab.article.model.Category;
import com.techlab.article.model.ProductType;

import java.time.LocalDate;

public final class ValidateUtil {
    private ValidateUtil() {
        throw new UnsupportedOperationException("Clase utilitaria, no se debe instanciar.");
    }

    public static String string(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("El valor de '" + message + "' no puede estar vacío");
        }
        return string.trim();
    }

    public static double price(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe tener un valor mayor a cero");
        }
        return price;
    }

    public static Category category(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("El producto debe tener una categoría");
        }
        return category;
    }

    public static ProductType productType(ProductType productType) {
        if (productType == null) {
            throw new IllegalArgumentException("El tipo de producto debe existir");
        }
        return productType;
    }

    public static int monthsOfWarranty(int monthsOfWarranty) {
        if (monthsOfWarranty < 0) {
            throw new IllegalArgumentException("La garantía no puede tener un valor menor a cero");
        }
        return monthsOfWarranty;
    }

    public static LocalDate expirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula");
        }
        if (expirationDate.isBefore(LocalDate.of(1999, 12, 31)) || expirationDate.isAfter(LocalDate.of(2100, 1, 1))) {
            throw new IllegalArgumentException("Fecha de vencimiento fuera de rango permitido");
        }
        return expirationDate;
    }
}
