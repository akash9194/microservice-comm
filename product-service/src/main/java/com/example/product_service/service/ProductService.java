package com.example.product_service.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
            return productRepository.findAll();
    }

    public Product getProduct(String id){
        return productRepository.findById(id).orElseThrow();
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public void decreaseStock(String id, int quantity){
        Product product = productRepository.findById(id).orElseThrow();
        if(product.getQuantity() < quantity){
            throw  new RuntimeException("Not enough stock");
        }

        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
    }
}
