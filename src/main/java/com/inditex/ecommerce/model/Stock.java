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
@Table(name = "STOCK")
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @Column(name="SIZE_ID")
    private int sizeId;
    private int quantity;
}
