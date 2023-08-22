package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Product;
import com.inditex.ecommerce.model.Size;
import com.inditex.ecommerce.model.Stock;
import com.inditex.ecommerce.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class  )
class ProductsServiceTest {
    private static final List<Integer> MESSAGE_PRODUCTS= Arrays.asList( 3, 2, 1);

    @InjectMocks
    private ProductsService productsServiceService;

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private LoadStocks loadStocks;
    @Mock
    private LoadSizes loadSizes;
    @Mock
    private CheckStockages checkStockages;

    @Test
    void whenGetVisibilityProducts_getAIntegerList() {
        List <Product> products = mockProductList();
        Map< Integer, List<Size>> sizes = mockSizeMap();
        HashMap<Integer, Stock> stocks = mockStockHash();
        List<Integer> productsId = products.stream().map( Product::getId ).collect( Collectors.toList());
        List<Integer> sizesId = new ArrayList<>();
        when( productsRepository.findAll() ).thenReturn( products );
        when( loadSizes.getSizes( productsId, sizesId ) ).thenReturn( sizes );
        when( loadStocks.getStocks( sizesId )).thenReturn( stocks );
        when( checkStockages.checkStocks( products, sizes, stocks )).thenReturn( MESSAGE_PRODUCTS );

        List<Integer> result = productsServiceService.getAllProducts();

        assertEquals( MESSAGE_PRODUCTS, result);
    }

    @Test
    void whenGetVisibilityProducts_getAError() {
        when( productsRepository.findAll() ).thenThrow( RuntimeException.class );

        List<Integer> result = productsServiceService.getAllProducts();

        assertEquals( 0, result.size());
    }

    private Map< Integer, List<Size>> mockSizeMap() {
        Size size = new Size(1,1, false, false);
        Map< Integer, List<Size>> sizeMap = new HashMap<>();
        sizeMap.put( 1, Arrays.asList( size ) );
        return sizeMap;
    }

    private HashMap<Integer, Stock> mockStockHash() {
        HashMap<Integer, Stock> o = null;
        return o;
    }

    private List<Product> mockProductList() {
        Product product_1 = new Product(1,16);
        Product product_2 = new Product(2,12);
        Product product_3 = new Product(3,6);
        return Arrays.asList( product_1, product_2 , product_3 );
    }

}
