package com.inditex.ecommerce.controller;

import com.inditex.ecommerce.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
class ProductControllerTest {
    private static final String PRODUCTS_SEARCH = "/products";

    private static final List<Integer> MESSAGE_PRODUCTS= Arrays.asList( 5, 6, 1);

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ProductsService productsService;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup( productsController ).build();
    }
    @Test
    void whenSearchProduct_thenReturnProductIdList() throws Exception {
        when( productsService.getAllProducts() ).thenReturn( MESSAGE_PRODUCTS );
        mvc.perform( get( PRODUCTS_SEARCH ) ).andExpect( status().isOk() ).andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( jsonPath( "$[0]" ).value( 5 ) )
                .andExpect( jsonPath( "$[1]" ).value( 6 ) )
                .andExpect( jsonPath( "$[2]" ).value( 1 ) ).andReturn();
    }
}
