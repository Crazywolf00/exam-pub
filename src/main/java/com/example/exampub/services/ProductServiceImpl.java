package com.example.exampub.services;

import com.example.exampub.models.Product;
import com.example.exampub.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllDrinks() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByID(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


}
