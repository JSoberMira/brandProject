package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Size;
import com.inditex.ecommerce.model.Stock;
import com.inditex.ecommerce.repository.SizesRepository;
import com.inditex.ecommerce.repository.StocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class LoadSizeTest {
    private static final Integer SIZE_ID_1 = 17;
    private static final Integer SIZE_ID_2=  18;
    private static final Integer PRODUCT_ID= 5;
    @InjectMocks
    private LoadSizes loadSizes;
    @Mock
    private SizesRepository sizesRepository;
    @Test

    void whenLoadSize_thenCorrect()  {
        Size size_1 = new Size(SIZE_ID_1, PRODUCT_ID, false, true);
        Size size_2 = new Size(SIZE_ID_2,PRODUCT_ID, false, false);
        List<Integer> products = new ArrayList<>();
        List<Integer> sizesList = new ArrayList<>();
        products.add( PRODUCT_ID );
        when( sizesRepository.findByProductIdIn( products ) ).thenReturn( Arrays.asList( size_1, size_2 ) );
        Map<Integer, List<Size>> sizes = loadSizes.getSizes(products, sizesList );

        assertNotNull( sizes );
        assertEquals(SIZE_ID_1, sizes.get( PRODUCT_ID ).get( 0 ).getId() );
        assertEquals(SIZE_ID_2, sizes.get( PRODUCT_ID ).get( 1 ).getId() );
        assertEquals(2, sizesList.size() );
    }
}
