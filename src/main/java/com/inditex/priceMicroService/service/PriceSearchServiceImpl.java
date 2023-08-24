package com.inditex.priceMicroService.service;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.exception.PriceNotFoundException;
import com.inditex.priceMicroService.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static com.inditex.priceMicroService.repository.util.PriceSpecificationUtil.searchFilter;

@Service
public class PriceSearchServiceImpl implements PriceSearchService {
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Prices searchPrices(final FilterPrices filterPrices) {
        return priceRepository.findAll( searchFilter( filterPrices ) )
                .stream().max( Comparator.comparing( Prices::getPriority ) ).orElseThrow( PriceNotFoundException::new );
    }


}
