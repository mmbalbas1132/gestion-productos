# Gestión de Productos

Sistema de gestión de productos en memoria implementado con Java y Lombok.

## Descripción

Este proyecto es un sistema completo de gestión de productos que utiliza almacenamiento en memoria (sin base de datos). Está desarrollado en Java 11 y utiliza la biblioteca Lombok para reducir el código repetitivo (boilerplate).

## Características

- ✅ **Gestión completa de productos**: Crear, leer, actualizar y eliminar productos
- ✅ **Control de inventario**: Gestión de stock con incremento y decremento
- ✅ **Búsqueda avanzada**: Buscar por ID, nombre, categoría
- ✅ **Validación de datos**: Validaciones en la capa de servicio
- ✅ **Almacenamiento en memoria**: Usando HashMap para persistencia temporal
- ✅ **Lombok**: Código limpio y mantenible con anotaciones

## Estructura del Proyecto

```
src/main/java/com/gestion/productos/
├── model/
│   └── Product.java          # Entidad de producto con anotaciones Lombok
├── repository/
│   └── ProductRepository.java # Repositorio en memoria
├── service/
│   └── ProductService.java    # Lógica de negocio
└── Main.java                  # Clase principal con demostración
```

## Tecnologías Utilizadas

- **Java 11**: Lenguaje de programación
- **Maven**: Gestión de dependencias y construcción
- **Lombok 1.18.30**: Generación automática de código
  - `@Data`: Getters, setters, toString, equals, hashCode
  - `@Builder`: Patrón builder para construcción de objetos
  - `@AllArgsConstructor` / `@NoArgsConstructor`: Constructores
  - `@RequiredArgsConstructor`: Constructor con campos finales
  - `@Log`: Logger automático

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/mmbalbas1132/gestion-productos.git
cd gestion-productos
```

2. Compilar el proyecto:
```bash
mvn clean compile
```

3. Ejecutar la aplicación:
```bash
mvn exec:java -Dexec.mainClass="com.gestion.productos.Main"
```

## Funcionalidades Implementadas

### Clase Product (modelo)
- ID autogenerado
- Nombre, descripción, precio, stock, categoría
- Timestamps de creación y actualización
- Métodos para gestionar stock (`addStock`, `reduceStock`)
- Método de disponibilidad (`isAvailable`)

### ProductRepository
- `save(Product)`: Guardar o actualizar producto
- `findById(Long)`: Buscar por ID
- `findAll()`: Listar todos los productos
- `findByCategory(String)`: Buscar por categoría
- `findByNameContaining(String)`: Búsqueda por nombre
- `deleteById(Long)`: Eliminar producto
- `count()`: Contar productos
- `existsById(Long)`: Verificar existencia
- `deleteAll()`: Limpiar repositorio

### ProductService
- `createProduct(Product)`: Crear producto con validaciones
- `updateProduct(Long, Product)`: Actualizar producto existente
- `getProductById(Long)`: Obtener producto por ID
- `getAllProducts()`: Listar todos los productos
- `getProductsByCategory(String)`: Filtrar por categoría
- `searchProductsByName(String)`: Buscar por nombre
- `deleteProduct(Long)`: Eliminar producto
- `increaseStock(Long, int)`: Aumentar inventario
- `decreaseStock(Long, int)`: Reducir inventario
- `getTotalProducts()`: Obtener cantidad total

## Ejemplo de Uso

```java
// Crear repositorio y servicio
ProductRepository repository = new ProductRepository();
ProductService productService = new ProductService(repository);

// Crear un producto usando Lombok Builder
Product laptop = Product.builder()
    .name("Laptop Dell XPS 15")
    .description("Laptop de alta gama")
    .price(new BigDecimal("1299.99"))
    .stock(10)
    .category("Electrónica")
    .build();

// Guardar el producto
laptop = productService.createProduct(laptop);

// Buscar producto por ID
Optional<Product> found = productService.getProductById(laptop.getId());

// Actualizar stock
productService.increaseStock(laptop.getId(), 5);
productService.decreaseStock(laptop.getId(), 2);

// Buscar por categoría
List<Product> electronics = productService.getProductsByCategory("Electrónica");

// Eliminar producto
productService.deleteProduct(laptop.getId());
```

## Ventajas de Usar Lombok

1. **Menos código repetitivo**: No es necesario escribir getters, setters, constructores
2. **Código más limpio**: Clases más legibles y mantenibles
3. **Builder pattern**: Construcción fluida de objetos
4. **Logging automático**: Anotación `@Log` para logging sencillo
5. **Actualización automática**: Al cambiar campos, los métodos se regeneran automáticamente

## Autor

Desarrollado por mmbalbas1132

## Licencia

Este proyecto está bajo la licencia MIT.
