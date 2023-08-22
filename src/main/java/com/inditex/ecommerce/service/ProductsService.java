package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Product;
import com.inditex.ecommerce.model.Size;
import com.inditex.ecommerce.model.Stock;
import com.inditex.ecommerce.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductsService {

    private List<Product> products;
    private Map<Integer, List<Size>> sizes;
    private Map<Integer, Stock> stocks;

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private LoadSizes loadSizes;
    @Autowired
    private LoadStocks loadStocks;
    @Autowired
    private CheckStockages checkStockages;


    public List<Integer> getAllProducts() {
        log.warn( "START LOAD DATA" );
        loadProductData();
        log.warn( "END LOAD DATA" );
        orderProducts();
        return checkStockages.checkStocks( products, sizes, stocks);
    }

    private void loadProductData() {
        try {
            products = productsRepository.findAll();
            List<Integer> productsId = products.stream().map( Product::getId ).toList();
            List<Integer> sizesList =new ArrayList<>();
            sizes = loadSizes.getSizes(productsId, sizesList);
            stocks = loadStocks.getStocks(sizesList);
        } catch (RuntimeException e) {
            log.error( e.getMessage() );
        }
    }

    private void orderProducts() {
        if (products!=null) {
            products.sort( Comparator.comparing( Product::getSequence ) );
        }
    }

}
