package com.example.shop14.repo;

import com.example.shop14.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findByProductId(Long productId);


    // так как этот метод может потенциально менять несколько строк в таблицах
    // имеет смысл сделать его транзакционным
    @Transactional // - выполнится только как одна транзакция
    void deleteByProductId(Long productId);
}
