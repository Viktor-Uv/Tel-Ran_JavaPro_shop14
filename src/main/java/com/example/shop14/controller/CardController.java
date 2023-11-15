package com.example.shop14.controller;

import com.example.shop14.entity.Card;
import com.example.shop14.entity.Product;
import com.example.shop14.repo.CardRepository;
import com.example.shop14.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
