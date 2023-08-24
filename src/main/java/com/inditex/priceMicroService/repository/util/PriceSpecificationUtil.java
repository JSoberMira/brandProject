package com.inditex.priceMicroService.repository.util;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.entity.PricesPK;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PriceSpecificationUtil {

    public static Specification<Prices> searchFilter(final FilterPrices filters){
        return (root, query, criteriaBuilder) -> {
            Predicate predicateProduct = criteriaBuilder.conjunction();
            Predicate predicateBrand = criteriaBuilder.conjunction();
            Predicate predicateDate = criteriaBuilder.conjunction();
            if ( filters.getProductId() != null && filters.getProductId() > 0 ) {
                predicateProduct = criteriaBuilder.equal( root.get( Prices.Fields.pricesId ).get( PricesPK.Fields.productId ), filters.getProductId() );
            }
            if ( filters.getBrandId() != null && filters.getBrandId() > 0 ) {
                predicateBrand = criteriaBuilder.equal( root.get( Prices.Fields.brandId ), filters.getBrandId() );
            }
            if ( filters.getApplicationDate() != null ) {
                predicateDate = criteriaBuilder.between( criteriaBuilder.literal( filters.getApplicationDate() ),
                        root.get( Prices.Fields.startDate ), root.get( Prices.Fields.endDate ) );
            }
            return criteriaBuilder.and( predicateBrand, predicateProduct, predicateDate );
        };
    }
}
