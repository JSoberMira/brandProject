package com.inditex.priceMicroService.repository.util;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static com.inditex.priceMicroService.repository.util.PriceSpecificationUtil.searchFilter;
import static com.inditex.priceMicroService.util.TestingUtils.mockFilterPricesDTO;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PriceSpecificationUtilTest {
    private CriteriaBuilder criteriaBuilderMock;
    private CriteriaQuery criteriaQueryMock;
    private Root<Prices> rootMock;
    @BeforeEach
    public void setUp() {
        criteriaBuilderMock = mock(CriteriaBuilder.class);
        criteriaQueryMock = mock(CriteriaQuery.class);
        rootMock = mock(Root.class);
    }

    @Test
    void searchFilter_FilterNotNull() {
        final FilterPrices filterPrices = mockFilterPricesDTO();
        final Path pathMock = mock( Path.class );
        when( this.rootMock.get( anyString() ) ).thenReturn( pathMock );
        when( pathMock.get( anyString() )).thenReturn( pathMock );
        final Specification<Prices> spec = searchFilter( filterPrices );
        spec.toPredicate(this.rootMock, this.criteriaQueryMock, this.criteriaBuilderMock);
        Mockito.verify(this.criteriaBuilderMock, Mockito.times(3 )).conjunction();
    }

    @Test
    void searchFilter_FilterDefaultValues() {
        final FilterPrices filterPrices = new FilterPrices();
        filterPrices.setProductId( 0 );
        filterPrices.setBrandId( 0 );
        final Specification<Prices> spec = searchFilter( filterPrices );
        spec.toPredicate(this.rootMock, this.criteriaQueryMock, this.criteriaBuilderMock);
        Mockito.verify(this.criteriaBuilderMock, Mockito.times(3 )).conjunction();
    }

    @Test
    void searchFilter_FilterNull() {
        final FilterPrices filterPrices = new FilterPrices();
        final Specification<Prices> spec = searchFilter( filterPrices );
        spec.toPredicate(this.rootMock, this.criteriaQueryMock, this.criteriaBuilderMock);
        Mockito.verify(this.criteriaBuilderMock, Mockito.times(3 )).conjunction();
    }
}
