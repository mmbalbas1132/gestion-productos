package com.manuel.proyecto.product.service;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.model.Product;

public class ProductValidator {
    public static void  validateProduct(Product p) throws InvalidProductException {
        if(p.getPrecio()<0){
            throw new InvalidProductException("El precio no puede ser negativo");
        }
        if(p.getNombre()==null || p.getNombre().isEmpty()){
            throw new InvalidProductException("El nombre no puede ser nulo o vacio");
        }
        if (p.getStock() < 0) {
            throw new InvalidProductException("El stock no puede ser negativo");
        }
    }
}
