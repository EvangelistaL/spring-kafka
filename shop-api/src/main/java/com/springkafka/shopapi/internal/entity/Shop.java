package com.springkafka.shopapi.internal.entity;

import com.springkafka.shopapi.internal.entity.enums.PurchaseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "shop")
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifier")
    @NotBlank
    private String identifier;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PurchaseStatus status;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "shop")
    private List<ShopItem> shopItems;
}
