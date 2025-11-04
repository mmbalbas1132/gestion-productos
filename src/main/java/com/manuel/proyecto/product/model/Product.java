package com.manuel.proyecto.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private long id;
    private String nombre;
    private double precio;
    private ProductCategory categoria;


}
