package com.inditex.priceMicroService.util;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.entity.PricesPK;

import java.time.LocalDateTime;

public final class TestingUtils {
    public static final LocalDateTime localDateTimeTest = LocalDateTime.of( 2020, 06, 14, 10, 00, 00 );

    public static Prices mockPrices() {
        PricesPK pricesPK = new PricesPK( 1, 3561 );
        Prices prices = new Prices( pricesPK, 1, localDateTimeTest, localDateTimeTest, 1, 25.6, "EUR" );
        return prices;
    }

    public static FilterPrices mockFilterPricesDTO() {
        return new FilterPrices(35455, 1, localDateTimeTest);
    }
    }
