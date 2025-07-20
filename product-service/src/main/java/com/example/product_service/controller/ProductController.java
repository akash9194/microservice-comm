package com.example.product_service.controller;

import com.example.product_service.model.Product;
import com.example.product_service.service.ProductEventPublisher;
import com.example.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductEventPublisher publisher;

    public ProductController (ProductService productService, ProductEventPublisher publisher){
        this.productService = productService;
        this.publisher = publisher;

    }

    @GetMapping
    public List<Product> getAll(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        publisher.publish("Product fetch: " + id);
        return productService.getProduct(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        publisher.publish("Product created: " + product.getName());
        return productService.createProduct(product);
    }

    @PutMapping("/{id}/decreaseStock")
    public void decreaseStock(@PathVariable String id, @RequestParam int quantity){
        System.out.println(quantity + id);
        productService.decreaseStock(id, quantity);
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishProduct(@RequestBody String productJson) {
        publisher.publish(productJson);  // this uses KafkaTemplate
        return ResponseEntity.ok("Published");
    }
}
