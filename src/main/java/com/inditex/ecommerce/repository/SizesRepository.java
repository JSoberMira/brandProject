package com.inditex.ecommerce.repository;

import com.inditex.ecommerce.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SizesRepository extends JpaRepository<Size, Integer> {
    List<Size> findByProductIdIn(Collection<Integer> productId);
}
