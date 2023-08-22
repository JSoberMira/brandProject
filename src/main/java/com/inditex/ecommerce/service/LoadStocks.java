package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Stock;
import com.inditex.ecommerce.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoadStocks {

    @Autowired
    private StocksRepository stocksRepository;

    public Map<Integer, Stock> getStocks(final List<Integer> sizeIds) {
        Map<Integer, Stock> stocks = new HashMap<>();
        List<Stock> stockList = stocksRepository.findBySizeIdIn( sizeIds );
        stockList.stream().forEach( stock -> stocks.put( stock.getSizeId(), stock) );
        return stocks;
    }
}
