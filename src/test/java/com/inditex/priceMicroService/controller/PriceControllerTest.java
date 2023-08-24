package com.inditex.priceMicroService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import com.inditex.priceMicroService.exception.PriceNotFoundException;
import com.inditex.priceMicroService.service.PriceSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.inditex.priceMicroService.util.TestingUtils.mockFilterPricesDTO;
import static com.inditex.priceMicroService.util.TestingUtils.mockPrices;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
class PriceControllerTest {

    private static final String PRICE_SEARCH = "/price";

    @InjectMocks
    private PriceSearchController priceSearchController;

    @Mock
    private PriceSearchService service;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup( priceSearchController ).setControllerAdvice( RestHandlerException.class ).build();
    }

    @Test
    void givenFilterDto_whenSearch_thenReturnPriceDto() throws Exception {
        final Prices prices = mockPrices();
        final FilterPrices filterPrices = mockFilterPricesDTO();
        when( service.searchPrices( filterPrices ) ).thenReturn( prices );
        mvc.perform( post( PRICE_SEARCH ).content( convertObjectToJsonString( filterPrices ) )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isOk() ).andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( jsonPath( "$.pricesId.productId" ).value( 3561 ) ).andReturn();
    }

    @Test
    void givenFilterDto_whenSearch_thenReturnPricesNotFound() throws Exception {
        final FilterPrices filterPrices = mockFilterPricesDTO();
        when( service.searchPrices( filterPrices ) ).thenThrow( PriceNotFoundException.class );
        mvc.perform( post( PRICE_SEARCH ).content( convertObjectToJsonString( filterPrices ) )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isNotFound() );

    }

    private String convertObjectToJsonString(FilterPrices filterPrices) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter writer =mapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString( filterPrices );
    }
}
