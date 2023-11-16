package com.example.shop14.controller;

import com.example.shop14.entity.Product;
import com.example.shop14.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
    Homework 23

    1. Создайте через initializr (https://start.spring.io/) maven проект shop14

    2. Добавьте зависимости web, h2, lombok, jpa, validation

    3. Добавьте в проект сущность (@Entity) Product с полями
        Long id (@Id)
        String name
        BigDecimal price
        boolean isActive

    4. Добавьте репозитори

    5. Создайте контроллер (@RestController) ProductController с методами
        public Product createProduct(Product) // POST /products
        public void deleteProduct(Long id) // DELETE /products/{id}
        public Product getProductByProductId(Long id) // GET /products/{id}
        public Product updateComment(Long id, Product product) // PUT /products/{id}
 */

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // PUT /products
    @PutMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        // проверить цену продукта - больше 0
        // проверить длину имени - не пустая и меньше или равна 50 символов
        return productRepository.save(product); // return product instance upon successful save
    }

    // DELETE /products/{id}
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /products/{id}
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElse(null); // returns searched instance only when searched id is present
    }

    // GET /products
    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
