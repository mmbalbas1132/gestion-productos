package com.gestion.productos;

import com.gestion.productos.model.Product;
import com.gestion.productos.repository.ProductRepository;
import com.gestion.productos.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase principal que demuestra el uso del sistema de gestión de productos.
 */
public class Main {
    
    public static void main(String[] args) {
        // Inicializar repositorio y servicio
        ProductRepository repository = new ProductRepository();
        ProductService productService = new ProductService(repository);
        
        System.out.println("=== SISTEMA DE GESTIÓN DE PRODUCTOS ===\n");
        
        // 1. Crear productos
        System.out.println("--- 1. Creando productos ---");
        Product laptop = Product.builder()
                .name("Laptop Dell XPS 15")
                .description("Laptop de alta gama con procesador Intel i7")
                .price(new BigDecimal("1299.99"))
                .stock(10)
                .category("Electrónica")
                .build();
        
        Product mouse = Product.builder()
                .name("Mouse Logitech MX Master")
                .description("Mouse inalámbrico ergonómico")
                .price(new BigDecimal("99.99"))
                .stock(25)
                .category("Accesorios")
                .build();
        
        Product keyboard = Product.builder()
                .name("Teclado Mecánico RGB")
                .description("Teclado mecánico con iluminación RGB")
                .price(new BigDecimal("149.99"))
                .stock(15)
                .category("Accesorios")
                .build();
        
        Product monitor = Product.builder()
                .name("Monitor Samsung 27\"")
                .description("Monitor 4K UHD de 27 pulgadas")
                .price(new BigDecimal("399.99"))
                .stock(8)
                .category("Electrónica")
                .build();
        
        laptop = productService.createProduct(laptop);
        mouse = productService.createProduct(mouse);
        keyboard = productService.createProduct(keyboard);
        monitor = productService.createProduct(monitor);
        
        System.out.println("Productos creados exitosamente\n");
        
        // 2. Listar todos los productos
        System.out.println("--- 2. Listando todos los productos ---");
        List<Product> allProducts = productService.getAllProducts();
        allProducts.forEach(p -> System.out.println(formatProduct(p)));
        System.out.println("Total de productos: " + productService.getTotalProducts() + "\n");
        
        // 3. Buscar por categoría
        System.out.println("--- 3. Buscando productos por categoría 'Electrónica' ---");
        List<Product> electronics = productService.getProductsByCategory("Electrónica");
        electronics.forEach(p -> System.out.println(formatProduct(p)));
        System.out.println();
        
        // 4. Buscar por nombre
        System.out.println("--- 4. Buscando productos que contengan 'Mouse' ---");
        List<Product> searchResults = productService.searchProductsByName("Mouse");
        searchResults.forEach(p -> System.out.println(formatProduct(p)));
        System.out.println();
        
        // 5. Actualizar un producto
        System.out.println("--- 5. Actualizando precio del laptop ---");
        laptop.setPrice(new BigDecimal("1199.99"));
        Product updatedLaptop = productService.updateProduct(laptop.getId(), laptop);
        System.out.println("Precio actualizado: " + formatProduct(updatedLaptop) + "\n");
        
        // 6. Gestionar stock
        System.out.println("--- 6. Gestionando stock ---");
        System.out.println("Stock del mouse antes: " + mouse.getStock());
        productService.decreaseStock(mouse.getId(), 5);
        Product updatedMouse = productService.getProductById(mouse.getId()).get();
        System.out.println("Stock del mouse después de vender 5: " + updatedMouse.getStock());
        
        productService.increaseStock(mouse.getId(), 10);
        updatedMouse = productService.getProductById(mouse.getId()).get();
        System.out.println("Stock del mouse después de agregar 10: " + updatedMouse.getStock() + "\n");
        
        // 7. Verificar disponibilidad
        System.out.println("--- 7. Verificando disponibilidad ---");
        Product product = productService.getProductById(laptop.getId()).get();
        System.out.println("¿" + product.getName() + " disponible? " + product.isAvailable() + "\n");
        
        // 8. Eliminar un producto
        System.out.println("--- 8. Eliminando producto ---");
        System.out.println("Eliminando keyboard (ID: " + keyboard.getId() + ")");
        boolean deleted = productService.deleteProduct(keyboard.getId());
        System.out.println("Producto eliminado: " + deleted);
        System.out.println("Total de productos después de eliminar: " + productService.getTotalProducts() + "\n");
        
        // 9. Listar productos finales
        System.out.println("--- 9. Listado final de productos ---");
        allProducts = productService.getAllProducts();
        allProducts.forEach(p -> System.out.println(formatProduct(p)));
        
        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
    
    /**
     * Formatea un producto para mostrarlo en consola.
     */
    private static String formatProduct(Product p) {
        return String.format("ID: %d | %s | Precio: $%.2f | Stock: %d | Categoría: %s",
                p.getId(), p.getName(), p.getPrice(), p.getStock(), p.getCategory());
    }
}
