package com.example.shop14;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean isActive;
}
