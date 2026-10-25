package com.techlab.article.menu;

import com.techlab.article.interfaces.Identifiable;
import com.techlab.article.repository.Repository;
import com.techlab.article.util.FormatUtil;
import com.techlab.article.util.InputUtil;

import java.util.Scanner;


public class ConsoleView {

    public static <T extends Identifiable> void listItems(Repository<T> repository, String title) {
        if (repository == null || repository.isEmpty()) {
            System.out.println("La " + title + " está vacía...");
            return;
        }
        System.out.println("==> " +  title.toUpperCase());
        int index = 1;
        for (T item : repository) {
            System.out.println(index++ + ". | " + item);
        }
    }

    public static <T extends Identifiable> String viewItemByName(Repository<T> repository, Scanner scanner, String title) {
        if (repository == null || repository.isEmpty()) {
            System.out.println("La lista de " + title + "s está vacía...");
            return "";
        }
        System.out.println("==> BUSCAR " + title.toUpperCase() + " POR NOMBRE");
        System.out.print("Ingrese el nombre: ");
        String name = InputUtil.enterString(scanner);
        T item = repository.getItemByName(name);
        if (item == null) {
            System.out.println(FormatUtil.capitalizeString(title) + " inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getName();
        }
    }

    public static <T extends Identifiable> String viewItemByCode(Repository<T> repository, Scanner scanner, String title) {
        if (repository == null || repository.isEmpty()) {
            System.out.println("La lista de " + title + "s está vacía...");
            return "";
        }
        System.out.println("==> BUSCAR " + title.toUpperCase() + " POR CÓDIGO");
        System.out.print("Ingrese el nombre: ");
        String code = InputUtil.enterString(scanner);
        T item = repository.getItemByCode(code);
        if (item == null) {
            System.out.println(FormatUtil.capitalizeString(title) + " inexistente...");
            return "";
        } else {
            System.out.println(item);
            return item.getCode();
        }
    }

}
