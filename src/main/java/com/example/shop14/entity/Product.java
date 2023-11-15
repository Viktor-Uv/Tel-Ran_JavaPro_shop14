package com.example.shop14.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString
// @EqualsAndHashCode
public class Product {
    @Id
    // стратегия автогенерации ключа
    // IDENTITY - ключ будет генериться автоматически увеличивающимся значением в колонке
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 50, nullable = false, unique = false)
    private String name;

    @Column(name = "PRODUCT_PRICE", columnDefinition = "Decimal(10, 2) default '0.0'", nullable = false)
    private BigDecimal price;

    @Column(name = "PRODUCT_IS_ACTIVE", columnDefinition = "Boolean default 'true'", nullable = false)
    private boolean isActive;

    @OneToMany(
            mappedBy = "product", // метит сущность Product основной в этом соотношении
            orphanRemoval = true, // если удаляем продукт, удаляем и все его комменты
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Comment> comments;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "product_card",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> cards = new HashSet<>();

    // добавление карты к продукту
    public void addCard(Card card) {
        cards.add(card); // добавление карты
        card.getProducts().add(this); // добавление товара в товары этой карты
    }

    // удаление карты из продукта и продукта из карты (двунаправленная связь)
    public void removeCard(long cardId) {
        // Получить экземпляр карты из Set<Card> этого продукта по cardId
        Card cardToRemove = cards.stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst()
                .orElse(null);

        if (cardToRemove != null) {
            // Если карта найдена, удалить её из Set<Card> этого продукта
            cards.remove(cardToRemove);
            // Удалить этот продукт из данной карты
            cardToRemove.getProducts().remove(this);
        }
    }
}
