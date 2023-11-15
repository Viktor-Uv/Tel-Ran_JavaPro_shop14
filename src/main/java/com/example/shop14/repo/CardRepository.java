package com.example.shop14.repo;

import com.example.shop14.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
// public interface CardRepository extends JpaRepository<Card, Long> {
}
