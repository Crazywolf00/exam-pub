package com.example.exampub.services;

import com.example.exampub.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllDrinks();

    Product getProductByID(Long id);

    Product saveProduct(Product product);
}
