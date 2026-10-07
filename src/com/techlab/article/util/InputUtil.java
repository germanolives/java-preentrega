package com.techlab.article.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class InputUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private InputUtil () {
        throw new UnsupportedOperationException("Clase utilitaria, no se debe instanciar.");
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

    public static LocalDate enterDate(Scanner scanner, LocalDate minDate, LocalDate maxDate) {
        while (true) {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String input = enterString(scanner);

            try {
                LocalDate date = LocalDate.parse(input, formatter);
                if (date.isBefore(minDate) || date.isAfter(maxDate)) {
                    System.out.println("Error: La fecha debe estar entre " + minDate + " y " + maxDate + ".");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Error: Fecha o formato inválido. Asegúrese de ingresar una fecha real en formato yyyy-MM-dd (ej: 2026-09-30).");
            }
        }
    }
}
