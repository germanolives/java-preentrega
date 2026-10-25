package com.techlab.article.menu;

import com.techlab.article.model.*;
import com.techlab.article.service.Catalog;
import com.techlab.article.service.Inventory;
import com.techlab.article.util.InputUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class ProductMenu {

    private final static String[] MENU_OPTIONS = { "salir", "listar items", "buscar item por nombre",
            "buscar item por código", "modificar item", "agregar item", "eliminar item" };

    private final static LocalDate MIN_DATE = LocalDate.of(1999, 12, 31);
    private final static LocalDate MAX_DATE = LocalDate.of(2100, 1, 1);

    public static boolean modifyItem(Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("El inventario no está disponible para actualizaciones...");
            return false;
        }
        System.out.println("==> MODIFICAR ITEM");
        String code = ConsoleView.viewItemByCode(inventory, scanner, "productos");
        if (code.isEmpty())
            return false;
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String name = InputUtil.enterString(scanner);
        double price = InputUtil.enterPrice(scanner);
        int monthsOfWarranty = 0;
        LocalDate expirationDate = null;
        Product item = inventory.getItemByCode(code);
        if (item instanceof ElectronicProduct) {
            monthsOfWarranty = InputUtil.enterInteger(scanner, "meses de garantía");
        } else if (item instanceof FoodProduct) {
            expirationDate = InputUtil.enterDate(scanner, MIN_DATE, MAX_DATE);
        }
        System.out.print("Confirma actualizar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(scanner)) {
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
        ProductType selectedProductType = MenuUtil.selectProductType(scanner);
        Category category = MenuUtil.selectCategory(catalog, scanner, selectedProductType);
        if (category == null) return false;
        String code;
        while (true) {
            System.out.print("Ingrese el código: ");
            code = InputUtil.enterString(scanner);
            if (!inventory.isItemCodeIntoRepository(code)) {
                break;
            }
            System.out.println("Ese código ya existe...");
        }
        System.out.print("Ingrese el nombre: ");
        String name = InputUtil.enterString(scanner);
        double price = InputUtil.enterPrice(scanner);
        Product item;
        switch (selectedProductType) {
            case ELECTRONIC -> {
                int monthsOfWarranty = InputUtil.enterInteger(scanner, "meses de garantía");
                item = new ElectronicProduct(code, name, price, category, monthsOfWarranty);
            }
            case FOOD -> {
                LocalDate expirationDate = InputUtil.enterDate(scanner, MIN_DATE, MAX_DATE);
                item = new FoodProduct(code, name, price, category, expirationDate);
            }
            default -> {
                throw new IllegalArgumentException("Tipo no soportado: " + selectedProductType);
            }
        }
        System.out.print("Confirma agregar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(scanner)) {
            inventory.addItemToRepository(item);
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
        String code = InputUtil.enterString(scanner);
        Product item = inventory.getItemByCode(code);
        if (item == null) {
            System.out.println("No existe ese producto en el inventario...");
            return false;
        }
        System.out.println("Producto a eliminar:");
        System.out.println(item);
        System.out.print("Confirma eliminar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(scanner)) {
            Product product = inventory.removeItemFromRepository(item.getCode());
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

}
