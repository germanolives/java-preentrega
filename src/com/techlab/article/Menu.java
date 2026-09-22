package com.techlab.article;

import java.util.Scanner;

public class Menu {
    private final static String[] MENU_OPTIONS = {"salir", "listar items", "buscar item por nombre", "buscar item por código", "modificar item" ,"agregar item", "eliminar item"};

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
            System.out.println("==> " + i + ". " + formatString(MENU_OPTIONS[i]));
        }
        System.out.println("==> " + 0 + ". " + formatString(MENU_OPTIONS[0]));
        System.out.println();
        System.out.print("Elija una  opción: ");
    }

    public static boolean confirmOperation (Scanner scanner) {
        while (true) {
            String option = enterOption(scanner);
            if (option.equalsIgnoreCase("si")) {
                return true;
            }else if (option.equalsIgnoreCase("no")) {
                return false;
            }else {
                System.out.println("Ingrese la opción correcta...");
            }
        }
    }

    public static String enterOption(Scanner scanner) {
        while (true) {
            String textUser = scanner.nextLine().trim();
            if (textUser != null && !textUser.isBlank()) {
                return textUser.trim();
            } else {
                System.out.println("La entrada no puede estar vacía");
            }
        }
    }

    public static String enterCode(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el código del producto: ");
            String textUser = scanner.nextLine().trim();
            if (textUser != null && !textUser.isBlank()) {
                return textUser.trim();
            } else {
                System.out.println("El código no puede estar vacía");
            }
        }
    }

    public static String enterName(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el nombre del producto: ");
            String textUser = scanner.nextLine().trim();
            if (textUser != null && !textUser.isBlank()) {
                return textUser.trim();
            } else {
                System.out.println("El nombre no puede estar vacío");
            }
        }
    }

    public static double enterPrice (Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el precio del producto: ");
            String textUser = scanner.nextLine().trim();
            try {
                double price = Double.parseDouble(textUser);
                if (price > 0) {
                    return price;
                }
                System.out.println("Error: El precio debe ser un número mayor a cero.");
            }
            catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un precio válido: " + e);
            }
        }
    }

    public static int enterStock (Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el stock del producto: ");
            String textUser = scanner.nextLine().trim();
            try {
                int stock = Integer.parseInt(textUser);
                if (stock >= 0) {
                    return stock;
                }
                System.out.println("Error: El stock no puede ser negativo.");
            }
            catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido:" + e);
            }
        }
    }

    public static boolean listItems(Inventory inventory) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío");
            return false;
        }
        System.out.println("==> LISTAR ITEMS");
        int index = 1;
        for (Product item : inventory.getProducts()) {
            System.out.println(index++ + ". Código: " + item.getCode() +
                    " | Nombre: " + item.getName() +
                    " | Precio: $" + item.getPrice() +
                    " | Stock: " + item.getStock());
        }
        /*inventory.getProducts().forEach(item -> System.out.println(
                "Código: " + item.getCode() +
                        " | Nombre: " + item.getName() +
                        " | Precio: $" + item.getPrice() +
                        " | Stock: " + item.getStock()));*/
        return true;
    }

    public static String viewItemByName (Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío");
            return "";
        }
        System.out.println("==> BUSCAR ITEM POR NOMBRE");
        System.out.print("Ingrese el nombre del producto: ");
        String name = enterOption(scanner);
        Product item = inventory.getProductByName(name);
        if (item == null) {
            System.out.println("Producto inexistente");
            return "";
        }else {
            System.out.println("Nombre: " + item.getName() +
                    " | Código: " + item.getCode() +
                    " | Precio: $" + item.getPrice() +
                    " | Stock: " + item.getStock());
            return item.getName();
        }
    }

    public static String viewItemByCode (Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío");
            return "";
        }
        System.out.println("==> BUSCAR ITEM POR CÓDIGO");
        System.out.print("Ingrese el código del producto: ");
        String code = enterOption(scanner);
        Product item = inventory.getProductByCode(code);
        if (item == null) {
            System.out.println("Producto inexistente");
            return "";
        }else {
            System.out.println("Código: " + item.getCode() +
                    " | Nombre: " + item.getName() +
                    " | Precio: $" + item.getPrice() +
                    " | Stock: " + item.getStock());
            return item.getCode();
        }
    }

    public static boolean modifyItem (Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("El inventario no está disponible para actualizaciones");
            return false;
        }
        System.out.println("==> MODIFICAR ITEM");
        String code = viewItemByCode(inventory, scanner);
        if (code.isEmpty()) return false;
        String name = enterName(scanner);
        double price = enterPrice(scanner);
        int stock = enterStock(scanner);
        System.out.println("Confirma actualizar? ('SI' -- 'NO')");
        if (confirmOperation(scanner)) {
            Product item = inventory.getProductByCode(code);
            item.setName(name);
            item.setPrice(price);
            item.setStock(stock);
            System.out.println("Producto con código '" + item.getCode() + "' actualizado");
            System.out.println(
                    " | Nombre: " + item.getName() +
                            " | Precio: $" + item.getPrice() +
                            " | Stock: " + item.getStock());
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }
    }

    public static boolean addItem (Inventory inventory, Scanner scanner) {
        if (inventory == null) {
            System.out.println("El inventario no está disponible");
            return false;
        }
        System.out.println("==> AGREGAR ITEM");
        String code;
        while (true) {
            code = enterCode(scanner);
            if (!inventory.isCodeIntoInventory(code)) {
                break;
            }
            System.out.println("Ese código ya existe...");
        }
        String name = enterName(scanner);
        double price = enterPrice(scanner);
        int stock = enterStock(scanner);
        System.out.println("Confirma agregar? ('SI' -- 'NO')");
        if (confirmOperation(scanner)) {
            Product item = new Product(code, name, price, stock);
            inventory.addProductToInventory(item);
            System.out.println("Producto con código '" + item.getCode() + "' agregado");
            System.out.println(
                    " | Nombre: " + item.getName() +
                            " | Precio: $" + item.getPrice() +
                            " | Stock: " + item.getStock());
            return true;
        }else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }

    }

    public static boolean deleteItem (Inventory inventory, Scanner scanner) {
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventario vacío");
            return false;
        }
        System.out.println("==> ELIMINAR ITEM");
        String code = enterCode(scanner);
        Product item = inventory.getProductByCode(code);
        if (item == null) {
            System.out.println("No existe ese producto en el inventario...");
            return false;
        }
        System.out.println("Producto a eliminar:");
        System.out.println("Código: " + item.getCode() +
                " | Nombre: " + item.getName() +
                " | Precio: $" + item.getPrice() +
                " | Stock: " + item.getStock());
        System.out.println("Confirma eliminar? ('SI' -- 'NO')");
        if (confirmOperation(scanner)) {
            inventory.removeProductFromInventory(item.getCode());
            System.out.println("Producto con código '" + code + "' eliminado");
            return true;
        } else {
            System.out.println("ℹ️ Operación cancelada por el usuario.");
            return false;
        }
    }

    public static void main (String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int menuOption;
        do {
            showMenuOptions();
            String textUser = enterOption(scanner);
            menuOption = validateOption(textUser, MENU_OPTIONS);
            switch (menuOption) {
                case 0 -> System.out.println("Saliendo del sistema...");
                case 1 -> listItems(inventory);
                case 2 -> viewItemByName(inventory, scanner);
                case 3 -> viewItemByCode(inventory, scanner);
                case 4 -> modifyItem(inventory, scanner);
                case 5 -> addItem(inventory, scanner);
                case 6 -> deleteItem(inventory, scanner);
                default -> System.out.println("Opción incorrecta...");
            }
        }while (menuOption !=0);
        scanner.close();
    }

}
