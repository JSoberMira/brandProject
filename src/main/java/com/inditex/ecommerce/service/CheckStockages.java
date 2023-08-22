package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Product;
import com.inditex.ecommerce.model.Size;
import com.inditex.ecommerce.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class CheckStockages {
    public List<Integer> checkStocks(final List<Product> products, final Map<Integer, List<Size>> sizes, final Map<Integer, Stock> stocks ) {
        List<Product> finalProducts = new ArrayList<>();
        products.stream().forEach( product -> {
            if (isVisibilityProduct(product, sizes, stocks)) {
                finalProducts.add( product );
            }

        } );
        return finalProducts.stream().map(Product::getId).toList();
    }

    private boolean isVisibilityProduct(final Product product, final Map<Integer, List<Size>> sizes, final Map<Integer, Stock> stocks) {
        AtomicBoolean show = new AtomicBoolean( false );
        AtomicBoolean haveSpecialWithStock = new AtomicBoolean( false );
        AtomicBoolean haveNotSpecialWithStock = new AtomicBoolean( false );
        List<Size> sizesProduct = ( sizes.containsKey( product.getId() ) ? sizes.get( product.getId() ) : new ArrayList<>() );
        sizesProduct.stream().forEach( size -> {
            if (stocks.containsKey( size.getId() ) ) {
                if (isBackSoon( size ) ) {
                    show.set( true );
                }
                if (isSpecialWithStock( size, stocks )) {
                    haveSpecialWithStock.set(true);
                }
                if (isNotSpecialWithStock( size, stocks )) {
                    haveNotSpecialWithStock.set( true );
                }
            }
        } );
        if (haveNotSpecialWithStock.get() && haveSpecialWithStock.get() ){
            show.set( true );
        }

        return show.get();
    }

    private boolean isSpecialWithStock(final Size size, final Map<Integer, Stock> stocks) {
        final Stock stock = stocks.get( size.getId() );
        return stock.getQuantity()>0 && size.isSpecial();
    }

    private boolean isBackSoon(final Size size) {
        return size.isBackSoon() && !size.isSpecial();
    }

    private boolean isNotSpecialWithStock(final Size size, final Map<Integer, Stock> stocks) {
        final Stock stock = stocks.get( size.getId() );
        return stock.getQuantity()>0 && !size.isSpecial();
    }
}
