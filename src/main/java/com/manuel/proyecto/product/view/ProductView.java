package com.manuel.proyecto.product.view;

import com.manuel.proyecto.product.controller.ProductController;
import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.model.Product;
import com.manuel.proyecto.product.model.ProductCategory;

import java.util.Optional;
import java.util.Scanner;


public class ProductView {
    private final ProductController productController;
    private final Scanner scanner;


    public ProductView( ProductController productController) {
        this.productController = productController;
        scanner = new Scanner(System.in);

    }

    private void showAllProducts() {
        try {
            System.out.println("\nLista de Productos:");
            for (Product product : productController.getAllProducts()) {
                System.out.println(product);
            }
        } catch (InvalidProductException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
    }

    private void showProduct(Product product1){
        try {
            long id = readValidLong("Ingrese el ID del producto a mostrar (mayor o igual a 0):", 0);
            Product product = productController.getProductById(id).orElse(null);
            if (product != null) {
                System.out.println("Detalles del Producto:");
                System.out.println(product);
            } else {
                System.out.println("Producto no encontrado con ID: " + id);
            }
        } catch (InvalidProductException e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());
        }
    }

    private void findProductById() {
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar (mayor o igual a 0):", 0);
            Optional<Product> product = productController.getProductById(id);
            if (product.isPresent()) {
                Product product1 = product.get();
                showProduct(product1);
            } else {
                System.out.println("Producto no encontrado con ID: " + id);
            }
        } catch (InvalidProductException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }
    }

    private void eliminarProduct() {
        try {
            long id = readValidLong("Ingrese el ID del producto a eliminar (mayor o igual a 0):", 0);
            productController.removeProduct(id);
            System.out.println("Producto eliminado exitosamente.");
        } catch (InvalidProductException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            long id = readValidLong("Ingrese el ID del producto a modificar (mayor o igual a 0):", 0);
          Optional<Product> product = productController.getProductById(id);
            if (product.isPresent()) {
                System.out.println("Producto a ACTUALIZAR");
                    Product product1 = product.get();
                    showProduct(product1);
                System.out.println("Seleccione el campo a modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Precio");
                System.out.println("3. Categoría");
                System.out.println("4. Stock");
                System.out.println("5. TODOS LOS CAMPOS");
                System.out.println("6. Salir sin modificar");
                 int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> {
                        String nombre = readNotEmptyString("Ingrese el nuevo nombre del producto:");
                        product1.setNombre(nombre);
                    }
                    case 2 -> {
                        double precio = readValidDouble("Ingrese el nuevo precio del producto (mayor o igual a 0):", 5);
                        product1.setPrecio(precio);
                    }
                    case 3 -> {
                        String categoria = readNotEmptyString("Ingrese la nueva categoría del producto: \nELECTRONICS, COMIDAS, LIBROS, OTROS");
                        product1.setCategoria(ProductCategory.valueOf(categoria.toUpperCase()));
                    }
                    case 4 -> {
                        int stock = readValidInteger("Ingrese el nuevo stock del producto (mayor o igual a 0):", 2);
                        product1.setStock(stock);
                    }
                    case 5 -> {
                        String nombre = readNotEmptyString("Ingrese el nuevo nombre del producto:");
                        double precio = readValidDouble("Ingrese el nuevo precio del producto (mayor o igual a 0):", 5);
                        int stock = readValidInteger("Ingrese el nuevo stock del producto (mayor o igual a 0):", 2);
                        String categoria = readNotEmptyString("Ingrese la nueva categoría del producto: \nELECTRONICS, COMIDAS, LIBROS, OTROS");

                        product1.setNombre(nombre);
                        product1.setPrecio(precio);
                        product1.setStock(stock);
                        product1.setCategoria(ProductCategory.valueOf(categoria.toUpperCase()));
                    }
                    case 6 -> {
                        System.out.println("Saliendo sin modificar el producto.");
                        return;
                    }
                    default -> {
                        System.out.println("Opción no válida. Saliendo sin modificar el producto.");
                        return;
                    }
                }
            }
        } catch (InvalidProductException e) {
            System.out.println("Error al modificar el producto: " + e.getMessage());
        }
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
                    case 1 -> addProduct();
                    case 2 -> showAllProducts();
                    case 3 -> findProductById();
                    case 4 -> eliminarProduct();
                    case 5 -> updateProduct();
                    case 6 -> {
                        System.out.println("Saliendo del menu de productos.");
                        scanner.close();
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
