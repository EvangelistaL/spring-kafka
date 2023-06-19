package com.springkafka.shopapi.internal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "shop_item")
public class ShopItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productIdentifier")
    @NotBlank
    @Size(max = 100)
    private String productIdentifier;

    @Column(name = "amount")
    @NotNull
    private Integer amount;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
