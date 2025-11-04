package com.manuel.proyecto.product.repository;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.exceptions.ProductNotFoundException;
import com.manuel.proyecto.product.interfaces.ProductRepository;
import com.manuel.proyecto.product.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryServices implements ProductRepository {

    private final List<Product> products = new ArrayList<>();



    @Override
    public List<Product> findAll() throws InvalidProductException {
        if(products.isEmpty()){
            throw new InvalidProductException("La lista de productos está vacía.");
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    @Override
    public void save(Product product) {
        products.add(product);

    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId() == id);

    }

    @Override
    public void update(Optional<Product> product) {
        product.ifPresent(p -> {
            int index = findIndexById(p.getId());
            if (index != -1) {
                products.set(index, p);
            }else {
                throw new ProductNotFoundException("Producto con ID " + p.getId() + " no encontrado para actualizar.");
            }

        });


    }

    @Override
    public boolean existsById(Long id) {
        return products.stream().anyMatch(product -> product.getId() == id);
    }

    private int findIndexById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
