package com.inditex.ecommerce.repository;


import com.inditex.ecommerce.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StocksRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findBySizeIdIn(Collection<Integer> sizeId);

}
