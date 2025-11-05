package com.manuel.proyecto.product.view;

import com.manuel.proyecto.product.controller.ProductController;
import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.model.Product;
import com.manuel.proyecto.product.model.ProductCategory;

import java.util.Scanner;

import static com.sun.beans.introspect.PropertyInfo.Name.description;

public class ProductView {
    private final ProductController productController;
    private final Scanner scanner;


    public ProductView( ProductController productController) {
        this.productController = productController;
        scanner = new Scanner(System.in);

    }

    public void showMenu() {
        while (true) {
            System.out.println("\nSeleccione un producto:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Salir");
            System.out.print("Elija una opcion: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                return;
                }
            }
        }
    }

    public void addProduct() {
        try {
            long id = readValidLong("Ingrese el ID del producto (mayor o igual a 0):", 0);
            String nombre = readNotEmptyString("Ingrese el nombre del producto:");
            double precio = readValidDouble("Ingrese el precio del producto (mayor o igual a 0):", 5);
            int stock = readValidInteger("Ingrese el stock del producto (mayor o igual a 0):", 2);
            String categoria = readNotEmptyString("Ingrese la categoría del producto: \nELECTRONICS, COMIDAS, LIBROS, OTROS");

            Product product = new Product(id, nombre, precio, ProductCategory.valueOf(categoria.toUpperCase()), stock);
            productController.addProduct(product);
            System.out.println("Producto agregado exitosamente.");
        } catch (InvalidProductException e) {
            System.out.println("Error al agregar el producto: " + e.getMessage());
        }
    }
    private String readNotEmptyString(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.length() < 3) {
                System.out.println("El valor ingresado no puede ser nulo o es muy corto. Intente nuevamente.");
            }
        } while (input.length() < 3);
        return input;
    }

    private long readValidLong(String message, long min) {
        long value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Long.parseLong(input);
                if (value < min) {
                    System.out.println("El valor ingresado debe ser mayor o igual a " + min + ". Intente nuevamente.");
                    continue;
                }
                return value;

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }

    private int readValidInteger(String message, long min) {
        int value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value < min) {
                    System.out.println("El valor ingresado debe ser mayor o igual a " + min + ". Intente nuevamente.");
                    continue;
                }
                return value;

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }

    private double readValidDouble(String message, long min) {
        double value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Double.parseDouble(input);
                if (value < min) {
                    System.out.println("El valor ingresado debe ser mayor o igual a " + min + ". Intente nuevamente.");
                    continue;
                }
                return value;

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }
}
