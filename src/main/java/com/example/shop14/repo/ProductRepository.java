package com.example.shop14.repo;

import com.example.shop14.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findProductsByCardsId(Long cardId);
}
