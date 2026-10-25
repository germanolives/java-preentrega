package com.techlab.article.menu;

import com.techlab.article.model.Category;
import com.techlab.article.model.ProductType;
import com.techlab.article.service.Catalog;
import com.techlab.article.util.FormatUtil;
import com.techlab.article.util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class MenuUtil {
    public static ProductType selectProductType(Scanner scanner) {
        ProductType[] types = ProductType.values();
        System.out.println("Tipos de producto");
        int index = 1;
        for (ProductType productType : types) {
            System.out.println(index++ + ". | " + FormatUtil.capitalizeString(productType.getDescription()));
        }
        while (true) {
            System.out.print("Seleccione (1 - " + types.length + "): ");
            try {
                int option = Integer.parseInt(InputUtil.enterString(scanner));
                if (option > 0 && option <= types.length) {
                    return types[option - 1];
                }
                System.out.println("Opción fuera de rango...");
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta, ingrese un número...");
            }
        }
    }

    public static Category selectCategory(Catalog catalog, Scanner scanner, ProductType productType) {
        List<Category> filterCatalog = catalog.getList().stream()
                .filter(item -> item.getProductType() == productType)
                .toList();
        if (filterCatalog.isEmpty()) {
            System.out.println("No hay categoría para tipo de producto '" + productType.getDescription() + "'");
            return null;
        }
        System.out.println("==> LISTA DE CATEGORÍAS");
        int index = 1;
        for (Category item : filterCatalog) {
            System.out.println(index++ + ". | " + item);
        }
        while (true) {
            System.out.print("Elija la categoría (1 - " + filterCatalog.size() + "): ");
            try {
                int option = Integer.parseInt(InputUtil.enterString(scanner));
                if (option > 0 && option <= filterCatalog.size()) {
                    return filterCatalog.get(option - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta...");
            }
        }
    }
}
