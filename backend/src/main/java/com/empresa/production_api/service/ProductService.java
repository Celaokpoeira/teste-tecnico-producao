package com.empresa.production_api.service;

import com.empresa.production_api.model.Product;
import com.empresa.production_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        if (product.getCompositions() != null) {
            product.getCompositions().forEach(comp -> comp.setProduct(product));
        }
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}