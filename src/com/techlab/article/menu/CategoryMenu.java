package com.techlab.article.menu;

import com.techlab.article.model.Category;
import com.techlab.article.model.ProductType;
import com.techlab.article.service.Catalog;
import com.techlab.article.service.Inventory;
import com.techlab.article.util.InputUtil;

import java.util.Scanner;

public class CategoryMenu {

    private final Inventory inventory;
    private final Catalog catalog;
    private final Scanner scanner;

    public CategoryMenu(Inventory inventory, Catalog catalog, Scanner scanner) {
        this.inventory = inventory;
        this.catalog = catalog;
        this.scanner = scanner;
    }

    public void listItems() {
        ConsoleView.listItems(this.catalog, "lista de categorías");
    }

    public void viewItemByName() {
        ConsoleView.viewItemByName(this.catalog, this.scanner, "categoría");
    }

    public void viewItemByCode() {
        ConsoleView.viewItemByCode(this.catalog, this.scanner, "categoría");
    }

    public boolean modifyCategory() {
        if (this.catalog == null || this.catalog.isEmpty()) {
            System.out.println("El catálogo no está disponible para actualizaciones");
            return false;
        }
        System.out.println("==> MODIFICAR CATEGORÍA");
        String code = ConsoleView.viewItemByCode(this.catalog, this.scanner, "categoría");
        if (code.isEmpty())
            return false;
        String name;
        while (true) {
            System.out.print("Ingrese el nuevo nombre de la categoría: ");
            name = InputUtil.enterString(this.scanner);
            if (!catalog.isItemNameIntoRepository(name)
                    || catalog.getIndexByCode(code) == this.catalog.getIndexByName(name)) {
                break;
            }
            System.out.println("Ese nombre ya existe en el catálogo...");
        }
        System.out.print("Ingrese la nueva descripción de la categoría: ");
        String description = InputUtil.enterString(this.scanner);
        System.out.print("Confirma actualizar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(this.scanner)) {
            Category item = this.catalog.getItemByCode(code);
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

    public boolean addCategory() {
        if (this.catalog == null) {
            System.out.println("El catálogo no está disponible");
            return false;
        }
        System.out.println("==> AGREGAR CATEGORÍA");
        String code;
        String name;
        ProductType selectedProductType;
        while (true) {
            System.out.println("--> Seleccione el tipo de producto de la categoría:");
            selectedProductType = MenuUtil.selectProductType(this.scanner);
            System.out.print("Ingrese el código: ");
            code = InputUtil.enterString(this.scanner);
            if (!this.catalog.isItemCodeIntoRepository(code)) {
                break;
            }
            System.out.println("Ese código de categoría ya existe...");
        }
        while (true) {
            System.out.print("Ingrese el nombre: ");
            name = InputUtil.enterString(this.scanner);
            if (!this.catalog.isItemNameIntoRepository(name)) {
                break;
            }
            System.out.println("Ese nombre de categoría ya existe...");
        }
        System.out.print("Ingrese la descripción: ");
        String description = InputUtil.enterString(this.scanner);
        System.out.print("Confirma agregar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(this.scanner)) {
            Category item = new Category(code, name, description, selectedProductType);
            catalog.addItemToRepository(item);
            System.out.println("Categoría agregada...");
            System.out.println(item);
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario...");
            return false;
        }

    }

    public boolean deleteCategory() {
        if (this.catalog == null || this.catalog.isEmpty()) {
            System.out.println("Catálogo vacío...");
            return false;
        }
        System.out.println("==> ELIMINAR CATEGORÍA");
        System.out.println("Ingrese el código de la categoría: ");
        String code = InputUtil.enterString(this.scanner);
        Category item = this.catalog.getItemByCode(code);
        if (item == null) {
            System.out.println("No existe esa categoría en el catálogo...");
            return false;
        }
        if (catalog.hasCategoryInInventory(code, this.inventory)) {
            System.out.println("Categoría asociada a productos en el inventario, no se puede eliminar...");
            return false;
        }

        System.out.println("Categoría a eliminar:");
        System.out.println(item);
        System.out.print("Confirma eliminar? ('SI' -- 'NO'): ");
        if (InputUtil.confirmOperation(this.scanner)) {
            Category category = this.catalog.removeItemFromRepository(item.getCode(), this.inventory);
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

}
