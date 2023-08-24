package com.inditex.priceMicroService.repository;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.entity.PricesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Prices, PricesPK>, JpaSpecificationExecutor<Prices> {

}
