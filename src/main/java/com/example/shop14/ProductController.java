package com.example.shop14;

import org.springframework.beans.factory.annotation.Autowired;
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

    // POST /products
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product); // return product instance upon successful save
    }

    // DELETE /products/{id}
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id); // deletes only if this id present
    }

    // GET /products/{id}
    @GetMapping("/products/{id}")
    public Product getProductByProductId(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElse(null); // returns searched instance only when searched id is present
    }

    // PUT /products/{id}
    @PutMapping("/products/{id}")
    public Product updateComment(@PathVariable Long id, @RequestBody Product product) {
        if (productRepository.existsById(id)) {
            return productRepository.save(product); // update existing instance by id
        } else {
            return null;
        }
    }
}
