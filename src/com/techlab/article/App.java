package com.techlab.article;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

import com.techlab.article.model.ElectronicProduct;
import com.techlab.article.model.FoodProduct;
import com.techlab.article.model.Product;
import com.techlab.article.service.Inventory;
import com.techlab.article.model.Category;
import com.techlab.article.service.Catalog;
import com.techlab.article.model.ProductType;

public class App {
    private final static String[] MENU_OPTIONS = {"salir", "listar items", "buscar item por nombre",
            "buscar item por código", "modificar item", "agregar item", "eliminar item", "listar categorías",
            "buscar categoría por nombre", "buscar categoría por código", "modificar categoría", "agregar categoría",
            "eliminar categoría"};

    private final static LocalDate MIN_DATE = LocalDate.of(1999, 12, 31);
    private final static LocalDate MAX_DATE = LocalDate.of(2100, 1, 1);

    public static int validateOption(String textUser, String[] options) {
        int output = -1;
        String optionNumber = Integer.toString(options.length);
        if (textUser != null && !textUser.isBlank() && textUser.length() <= optionNumber.length()) {
            int charDigit = 0;
            for (int i = 0; i < textUser.length(); i++) {
                if (Character.isDigit(textUser.charAt(i))) {
                    charDigit++;
                }
            }
            if (charDigit == textUser.length()) {
                int inputDigit = Integer.parseInt(textUser);
                if (inputDigit >= 0 && inputDigit <= options.length - 1) {
                    output = inputDigit;
                }
            }
        }
        return output;
    }

    public static String formatString(String chain) {
        if (chain == null || chain.isBlank()) {
            return "";
        }
        chain = chain.toLowerCase().trim();
        String[] words = chain.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String rest = word.substring(1);
                stringBuilder.append(firstLetter).append(rest).append(" ");
            }
        }
        chain = stringBuilder.toString().trim();
        return chain;
    }

    public static void showMenuOptions() {
        System.out.println("============================================");
        System.out.println("======  SISTEMA INVENTARIO - TECHLAB  ======");
        System.out.println("============================================");
        for (int i = 1; i < MENU_OPTIONS.length; i++) {
            if (i < 10) {
                System.out.println("==>  " + i + ". " + formatString(MENU_OPTIONS[i]));
            } else {
                System.out.println("==> " + i + ". " + formatString(MENU_OPTIONS[i]));
            }
        }
        System.out.println("==>  " + 0 + ". " + formatString(MENU_OPTIONS[0]));
        System.out.println();
        System.out.print("Elija una  opción: ");
    }

    public static boolean confirmOperation(Scanner scanner) {
        while (true) {
            String option = enterString(scanner);
            if (option.equalsIgnoreCase("si")) {
                return true;
            } else if (option.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Ingrese la opción correcta...");
            }
        }
    }

    public static String enterString(Scanner scanner) {
        while (true) {
            String textUser = scanner.nextLine().trim();
            if (textUser != null && !textUser.isBlank()) {
                return textUser;
            } else {
                System.out.println("La entrada no puede estar vacía...");
            }
        }
    }

    public static double enterPrice(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el precio del producto: ");
            String textUser = scanner.nextLine().trim();
            try {
                double price = Double.parseDouble(textUser);
                if (price > 0) {
                    return price;
                }
                System.out.println("Error: El precio debe ser un número mayor a cero...");
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un precio válido: " + e);
            }
        }
    }

    public static int enterInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print("Ingrese " + message + " del producto: ");
            String textUser = scanner.nextLine().trim();
            try {
                int inputNumber = Integer.parseInt(textUser);
                if (inputNumber >= 0) {
                    return inputNumber;
                }
                System.out.println("Error: El número no puede ser negativo...");
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido:" + e);
            }
        }
    }

    public static LocalDate enterDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String input = enterString(scanner);

            try {
                LocalDate date = LocalDate.parse(input, formatter);
                if (date.isBefore(MIN_DATE) || date.isAfter(MAX_DATE)) {
                    System.out.println("Error: La fecha debe estar entre " + MIN_DATE + " y " + MAX_DATE + ".");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Error: Fecha o formato inválido. Asegúrese de ingresar una fecha real en formato yyyy-MM-dd (ej: 2026-09-30).");
            }
        }
    }

    public static boolean listItems(Inventory inventory) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío...");
            return false;
        }
        System.out.println("==> LISTA DE PRODUCTOS");
        int index = 1;
        for (Product item : inventory) {
            System.out.println(index++ + ". | " + item);
        }
        return true;
    }

    public static String viewItemByName(Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío...");
            return "";
        }
        System.out.println("==> BUSCAR ITEM POR NOMBRE");
        System.out.print("Ingrese el nombre del producto: ");
        String name = enterString(scanner);
        Product item = inventory.getProductByName(name);
        if (item == null) {
            System.out.println("Producto inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getName();
        }
    }

    public static String viewItemByCode(Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío...");
            return "";
        }
        System.out.println("==> BUSCAR ITEM POR CÓDIGO");
        System.out.print("Ingrese el código del producto: ");
        String code = enterString(scanner);
        Product item = inventory.getProductByCode(code);
        if (item == null) {
            System.out.println("Producto inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getCode();
        }
    }

    public static boolean modifyItem(Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("El inventario no está disponible para actualizaciones...");
            return false;
        }
        System.out.println("==> MODIFICAR ITEM");
        String code = viewItemByCode(inventory, scanner);
        if (code.isEmpty())
            return false;
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String name = enterString(scanner);
        double price = enterPrice(scanner);
        int monthsOfWarranty = 0;
        LocalDate expirationDate = null;
        Product item = inventory.getProductByCode(code);
        if (item instanceof ElectronicProduct) {
            monthsOfWarranty = enterInteger(scanner, "meses de garantía");
        } else if (item instanceof FoodProduct) {
            expirationDate = enterDate(scanner);
        }
        System.out.print("Confirma actualizar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            item.setName(name);
            item.setPrice(price);
            if (item instanceof ElectronicProduct) {
                ((ElectronicProduct) item).setWarranty(monthsOfWarranty);
            } else if (item instanceof FoodProduct) {
                ((FoodProduct) item).setExpirationDate(expirationDate);
            }
            System.out.println("Producto actualizado...");
            System.out.println(item);
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario...");
            return false;
        }
    }

    public static boolean addItem(Inventory inventory, Catalog catalog, Scanner scanner) {
        if (inventory == null) {
            System.out.println("El inventario no está disponible...");
            return false;
        }
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("No hay categorías para poder agregar productos...");
            return false;
        }
        System.out.println("==> AGREGAR ITEM");
        ProductType selectedProductType = selectProductType(scanner);
        Category category = selectCategory(catalog, scanner, selectedProductType);
        String code;
        while (true) {
            System.out.print("Ingrese el código: ");
            code = enterString(scanner);
            if (!inventory.isProductIntoInventory(code)) {
                break;
            }
            System.out.println("Ese código ya existe...");
        }
        System.out.print("Ingrese el nombre: ");
        String name = enterString(scanner);
        double price = enterPrice(scanner);
        Product item;
        switch (selectedProductType) {
            case ELECTRONIC -> {
                int monthsOfWarranty = enterInteger(scanner, "meses de garantía");
                item = new ElectronicProduct(code, name, price, category, monthsOfWarranty);
            }
            case FOOD -> {
                LocalDate expirationDate = enterDate(scanner);
                item = new FoodProduct(code, name, price, category, expirationDate);
            }
            default -> {
                throw new IllegalArgumentException("Tipo no soportado: " + selectedProductType);
            }
        }
        System.out.print("Confirma agregar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            inventory.addProductToInventory(item);
            System.out.println("Producto agregado...");
            System.out.println(item);
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario...");
            return false;
        }

    }

    public static boolean deleteItem(Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío...");
            return false;
        }
        System.out.println("==> ELIMINAR ITEM");
        System.out.println("Ingrese el código del producto: ");
        String code = enterString(scanner);
        Product item = inventory.getProductByCode(code);
        if (item == null) {
            System.out.println("No existe ese producto en el inventario...");
            return false;
        }
        System.out.println("Producto a eliminar:");
        System.out.println(item);
        System.out.print("Confirma eliminar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            Product product = inventory.removeProductFromInventory(item.getCode());
            if (product != null) {
                System.out.println("Producto eliminado...");
                System.out.println(product);
                return true;
            }
            System.out.println("No se pudo realizar la eliminación de ese producto...");
            return false;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }
    }

    public static ProductType selectProductType(Scanner scanner) {
        ProductType[] types = ProductType.values();
        System.out.println("Tipos de producto");
        int index = 1;
        for (ProductType productType : types) {
            System.out.println(index++ + ". | " + formatString(productType.getDescription()));
        }
        while (true) {
            System.out.print("Seleccione (1 - " + types.length + "): ");
            try {
                int option = Integer.parseInt(enterString(scanner));
                if (option > 0 && option <= types.length) {
                    return types[option-1];
                }
                System.out.println("Opción fuera de rango...");
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta, ingrese un número...");
            }
        }
    }

    public static Category selectCategory(Catalog catalog, Scanner scanner, ProductType productType) {
        List<Category> filterCatalog = catalog.getCategories().stream()
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
                int option = Integer.parseInt(enterString(scanner));
                if (option > 0 && option <= filterCatalog.size()) {
                    return filterCatalog.get(option - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta...");
            }
        }
    }

    public static boolean listCategories(Catalog catalog) {
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("Catálogo vacío...");
            return false;
        }
        System.out.println("==> LISTA DE CATEGORÍAS");
        int index = 1;
        for (Category item : catalog) {
            System.out.println(index++ + ". | " + item);
        }
        return true;
    }

    public static String viewCateroryByName(Catalog catalog, Scanner scanner) {
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("Catálogo vacío");
            return "";
        }
        System.out.println("==> BUSCAR CATEGORÍA POR NOMBRE");
        System.out.print("Ingrese el nombre de la categoría: ");
        String name = enterString(scanner);
        Category item = catalog.getCategoryByName(name);
        if (item == null) {
            System.out.println("Categoría inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getName();
        }
    }

    public static String viewCategoryByCode(Catalog catalog, Scanner scanner) {
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("Catálogo vacío...");
            return "";
        }
        System.out.println("==> BUSCAR CATEGORÍA POR CÓDIGO");
        System.out.print("Ingrese el código de la categoría: ");
        String code = enterString(scanner);
        Category item = catalog.getCategoryByCode(code);
        if (item == null) {
            System.out.println("Categoría inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getCode();
        }
    }

    public static boolean modifyCategory(Catalog catalog, Scanner scanner) {
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("El catálogo no está disponible para actualizaciones");
            return false;
        }
        System.out.println("==> MODIFICAR CATEGORÍA");
        String code = viewCategoryByCode(catalog, scanner);
        if (code.isEmpty())
            return false;
        String name;
        while (true) {
            System.out.print("Ingrese el nuevo nombre de la categoría: ");
            name = enterString(scanner);
            if (!catalog.isCategoryNameIntoCatalog(name)
                    || catalog.getIndexByCode(code) == catalog.getIndexByName(name)) {
                break;
            }
            System.out.println("Ese nombre ya existe en el catálogo...");
        }
        System.out.print("Ingrese la nueva descripción de la categoría: ");
        String description = enterString(scanner);
        System.out.print("Confirma actualizar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            Category item = catalog.getCategoryByCode(code);
            item.setName(name);
            item.setDescription(description);
            System.out.println("Categoría actualizada...");
            System.out.println(item);
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }
    }

    public static boolean addCategory(Catalog catalog, Scanner scanner) {
        if (catalog == null) {
            System.out.println("El catálogo no está disponible");
            return false;
        }
        System.out.println("==> AGREGAR CATEGORÍA");
        String code;
        String name;
        ProductType selectedProductType;
        while (true) {
            System.out.println("--> Seleccione el tipo de producto de la categoría:");
            selectedProductType = selectProductType(scanner);
            System.out.print("Ingrese el código: ");
            code = enterString(scanner);
            if (!catalog.isCategoryCodeIntoCatalog(code)) {
                break;
            }
            System.out.println("Ese código de categoría ya existe...");
        }
        while (true) {
            System.out.print("Ingrese el nombre: ");
            name = enterString(scanner);
            if (!catalog.isCategoryNameIntoCatalog(name)) {
                break;
            }
            System.out.println("Ese nombre de categoría ya existe...");
        }
        System.out.print("Ingrese la descripción: ");
        String description = enterString(scanner);
        System.out.print("Confirma agregar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            Category item = new Category(code, name, description, selectedProductType);
            catalog.addCategoryToCatalog(item);
            System.out.println("Categoría agregada...");
            System.out.println(item);
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario...");
            return false;
        }

    }

    public static boolean deleteCategory(Catalog catalog, Inventory inventory, Scanner scanner) {
        if (catalog == null || catalog.isEmpty()) {
            System.out.println("Catálogo vacío...");
            return false;
        }
        System.out.println("==> ELIMINAR CATEGORÍA");
        System.out.println("Ingrese el código de la categoría: ");
        String code = enterString(scanner);
        Category item = catalog.getCategoryByCode(code);
        if (item == null) {
            System.out.println("No existe esa categoría en el catálogo...");
            return false;
        }
        if (catalog.hasCategoryInInventory(code, inventory)) {
            System.out.println("Categoría asociada a productos en el inventario, no se puede eliminar...");
            return false;
        }

        System.out.println("Categoría a eliminar:");
        System.out.println(item);
        System.out.print("Confirma eliminar? ('SI' -- 'NO'): ");
        if (confirmOperation(scanner)) {
            Category category = catalog.removeCategoryFromCatalog(item.getCode(), inventory);
            if (category != null) {
                System.out.println("Categoría eliminada...");
                System.out.println(category);
                return true;
            }
            System.out.println("No se pudo realizar la eliminación de esa categoría...");
            return false;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        int menuOption;
        do {
            showMenuOptions();
            String textUser = enterString(scanner);
            menuOption = validateOption(textUser, MENU_OPTIONS);
            switch (menuOption) {
                case 0 -> System.out.println("Saliendo del sistema...");
                case 1 -> listItems(inventory);
                case 2 -> viewItemByName(inventory, scanner);
                case 3 -> viewItemByCode(inventory, scanner);
                case 4 -> modifyItem(inventory, scanner);
                case 5 -> addItem(inventory, catalog, scanner);
                case 6 -> deleteItem(inventory, scanner);
                case 7 -> listCategories(catalog);
                case 8 -> viewCateroryByName(catalog, scanner);
                case 9 -> viewCategoryByCode(catalog, scanner);
                case 10 -> modifyCategory(catalog, scanner);
                case 11 -> addCategory(catalog, scanner);
                case 12 -> deleteCategory(catalog, inventory, scanner);
                default -> System.out.println("Opción incorrecta...");
            }
        } while (menuOption != 0);
        scanner.close();
    }

}
