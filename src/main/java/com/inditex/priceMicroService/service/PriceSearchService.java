package com.inditex.priceMicroService.service;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;

public interface PriceSearchService {
    Prices searchPrices(final FilterPrices filterPrices);
}
