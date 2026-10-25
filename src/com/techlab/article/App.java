package com.techlab.article;

import java.util.Scanner;

import com.techlab.article.service.Inventory;
import com.techlab.article.service.Catalog;
import com.techlab.article.util.FormatUtil;
import com.techlab.article.util.InputUtil;
import com.techlab.article.util.ValidateUtil;
import com.techlab.article.menu.ProductMenu;
import com.techlab.article.menu.CategoryMenu;

public class App {
    private final static String[] MENU_OPTIONS = { "salir", "listar items", "buscar item por nombre",
            "buscar item por código", "modificar item", "agregar item", "eliminar item", "listar categorías",
            "buscar categoría por nombre", "buscar categoría por código", "modificar categoría", "agregar categoría",
            "eliminar categoría" };

    public static void showMenuOptions() {
        System.out.println("============================================");
        System.out.println("======  SISTEMA INVENTARIO - TECHLAB  ======");
        System.out.println("============================================");
        for (int i = 1; i < MENU_OPTIONS.length; i++) {
            if (i < 10) {
                System.out.println("==>  " + i + ". " + FormatUtil.capitalizeString(MENU_OPTIONS[i]));
            } else {
                System.out.println("==> " + i + ". " + FormatUtil.capitalizeString(MENU_OPTIONS[i]));
            }
        }
        System.out.println("==>  " + 0 + ". " + FormatUtil.capitalizeString(MENU_OPTIONS[0]));
        System.out.println();
        System.out.print("Elija una  opción: ");
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        ProductMenu productMenu = new ProductMenu(inventory, catalog, scanner);
        CategoryMenu categoryMenu = new CategoryMenu(inventory, catalog, scanner);
        int menuOption;
        do {
            showMenuOptions();
            String textUser = InputUtil.enterString(scanner);
            menuOption = ValidateUtil.optionMenu(textUser, MENU_OPTIONS);
            switch (menuOption) {
                case 0 -> System.out.println("Saliendo del sistema...");
                case 1 -> productMenu.listItems();
                case 2 -> productMenu.viewItemByName();
                case 3 -> productMenu.viewItemByCode();
                case 4 -> productMenu.modifyItem();
                case 5 -> productMenu.addItem();
                case 6 -> productMenu.deleteItem();
                case 7 -> categoryMenu.listItems();
                case 8 -> categoryMenu.viewItemByName();
                case 9 -> categoryMenu.viewItemByCode();
                case 10 -> categoryMenu.modifyCategory();
                case 11 -> categoryMenu.addCategory();
                case 12 -> categoryMenu.deleteCategory();
                default -> System.out.println("Opción incorrecta...");
            }
        } while (menuOption != 0);
        scanner.close();
    }

}
