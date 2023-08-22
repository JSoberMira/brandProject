package com.inditex.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table( name = "SIZE")
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    @Id
    private int id;
    @Column(name = "PRODUCT_ID")
    private int productId;
    @Column(name = "BACK_SOON")
    private boolean backSoon;
    private boolean special;
}
