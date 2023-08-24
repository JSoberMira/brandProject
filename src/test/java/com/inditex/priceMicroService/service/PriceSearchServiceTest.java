package com.inditex.priceMicroService.service;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.exception.PriceNotFoundException;
import com.inditex.priceMicroService.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;

import static com.inditex.priceMicroService.util.TestingUtils.mockFilterPricesDTO;
import static com.inditex.priceMicroService.util.TestingUtils.mockPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PriceSearchServiceTest {


    @InjectMocks
    private PriceSearchServiceImpl priceSearchService;
    @Mock
    private PriceRepository priceRepository;


    @Test
    void priceSearchService_FilterPrice() {
        final FilterPrices filterPrices = mockFilterPricesDTO();
        final Prices prices = mockPrices();
        when( priceRepository.findAll(any( Specification.class)) ).thenReturn( Collections.singletonList( prices ) );
        final Prices result = priceSearchService.searchPrices( filterPrices );
        assertEquals( result, prices );
    }

    @Test
    void priceSearchService_FilterPrice_Empty() {
        final FilterPrices filterPrices = new FilterPrices();
        when( priceRepository.findAll(any( Specification.class)) ).thenReturn( Collections.emptyList() );
        assertThrows( PriceNotFoundException.class, () -> priceSearchService.searchPrices( filterPrices ));
    }

}
