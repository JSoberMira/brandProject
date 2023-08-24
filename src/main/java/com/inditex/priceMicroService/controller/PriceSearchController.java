package com.inditex.priceMicroService.controller;

import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.service.PriceSearchService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class PriceSearchController {
    @Autowired
    private PriceSearchService priceSearchService;
    @PostMapping(value = "/price",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse( responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Prices.class)) }),
            @ApiResponse(content = { @Content(mediaType = "application/json" )}, responseCode = "404")
    })
    Prices search(@RequestBody FilterPrices filterPrices) {
        return priceSearchService.searchPrices( filterPrices );
    }


}
