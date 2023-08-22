package com.inditex.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table( name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private int id;
    private int sequence;

}
