package com.example.shop14.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(
            mappedBy = "cards",
            cascade = {
                    CascadeType.ALL,
                    CascadeType.MERGE
            }
    )
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

}
