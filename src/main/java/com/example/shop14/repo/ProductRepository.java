package com.example.shop14.repo;

import com.example.shop14.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    // https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods.query-creation
    Iterable<Product> findProductsByCardsId(Long cardId);


    // https://www.baeldung.com/spring-data-jpa-query

    // for complex queries:

    // find all products with price in diapason
    @Query(
            nativeQuery = true, // используем native sql язык базы данных
            value = "SELECT * FROM products WHERE product_price > :from AND product_price < :to"
    )
    Iterable<Product> getProductsWithPricesInBetween(BigDecimal from, BigDecimal to); // same as above

    // JPQL, "надSQL" Hibernate
    @Query(value = "select p from Product p where p.isActive = :status") // ссылаемся не на таблицу products,
                                                                         // а на название Entity Product
    Iterable<Product> getProductsWithStatus(boolean status);


    // для реализации пейджинга
    @Query(value = "select p from Product p order by p.id")
    Page<Product> getPage(Pageable pageable);

    @Query(value = "select p from Product p")
    Iterable<Product> getAll(Sort sort);
}
