package com.example.shop14.controller;

import com.example.shop14.entity.Card;
import com.example.shop14.entity.Product;
import com.example.shop14.repo.CardRepository;
import com.example.shop14.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
public class CardController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<HttpStatus> deleteCardById(
            @PathVariable Long id
    ) {
        List<Product> products = new ArrayList<>();

        // нужно найти все продукты в данной карте
        productRepository.findProductsByCardsId(id).forEach(products::add);
        // в найденных продуктах удалить запись об этой карте
        products.forEach(
                p -> p.removeCard(id)
        );
        // и перезаписать продукты уже без карты
        productRepository.saveAll(products);

        // только потом удалить карту из репозитори
        cardRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE http://localhost:8080/products/{productId}/cards/{cardId} - удаление продукта из карты
    @DeleteMapping("/products/{productId}/cards/{cardId}")
    public ResponseEntity<HttpStatus> deleteCardFromProduct(
            @PathVariable Long productId,
            @PathVariable Long cardId
    ) {
        // Find the product by the id provided
        Product product = productRepository.findById(productId)
                .orElse(null);
        if (product == null)
            return ResponseEntity.notFound().build();

        // If product exists, remove the card associated with it from the linking table by the cardId
        // (because of M : M, where Product is marked as main)
        product.removeCard(cardId);

        // Save the updated product with the card association removed
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // POST http://localhost:8080/products/{productId}/cards - создание карты для продукта
    @PostMapping("/products/{productId}/cards")
    public ResponseEntity<Card> addCard(
            @PathVariable Long productId,
            @RequestBody Card cardRequest
    ) {
        // Find the product by the id provided
        Product product = productRepository.findById(productId)
                .orElse(null);
        if (product == null)
            return ResponseEntity.notFound().build();

        Long cardId = cardRequest.getId();
        if (cardId != null && cardId != 0L) {
            // Check if the card exists in the repository
            Card _card = cardRepository.findById(cardId)
                    .orElse(null);
            if (_card == null) {
                // If not - return "not found"
                return ResponseEntity.notFound().build();
            } else {
                // If so - Add the existing card to the product
                product.addCard(_card);
                productRepository.save(product);
                return ResponseEntity.ok(_card);
            }
        }

        // If the card doesn't have an ID or is a new card, add it to the product
        product.addCard(cardRequest);

        return ResponseEntity.ok(cardRepository.save(cardRequest));
    }

    // GET http://localhost:8080/cards/{cardId}/products - все продукты для карты
    @GetMapping("/cards/{cardId}/products")
    public ResponseEntity<Iterable<Product>> getAllProductsByCardId(
            @PathVariable Long cardId
    ) {
        if (!cardRepository.existsById(cardId)) {
            // If the card doesn't exist, return a not found response
            return ResponseEntity.notFound().build();
        } else {
            // Return all products associated with the specified card
            return ResponseEntity.ok(
                    productRepository.findProductsByCardsId(cardId)
            );
        }
    }
}
