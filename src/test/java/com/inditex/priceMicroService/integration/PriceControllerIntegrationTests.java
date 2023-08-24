package com.inditex.priceMicroService.integration;

import com.inditex.priceMicroService.PriceMicroServiceApplication;
import com.inditex.priceMicroService.dto.FilterPrices;
import com.inditex.priceMicroService.entity.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PriceMicroServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class PriceControllerIntegrationTests
{
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_14_10() {
        LocalDateTime localDateTime = LocalDateTime.of( 2020,06,14,10,00,00 );
        FilterPrices filterPrices = new FilterPrices(35455, 1, localDateTime);
        ResponseEntity<Prices> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals( 35.50, responseEntity.getBody().getPrice() );
    }

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_14_16() {
        LocalDateTime localDateTime = LocalDateTime.of( 2020,06,14,16,00,00 );
        FilterPrices filterPrices = new FilterPrices(35455, 1, localDateTime);
        ResponseEntity<Prices> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals( 25.45, responseEntity.getBody().getPrice() );
    }

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_14_21() {
        LocalDateTime localDateTime = LocalDateTime.of( 2020,06,14,21,00,00 );
        FilterPrices filterPrices = new FilterPrices(35455, 1, localDateTime);
        ResponseEntity<Prices> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals( 35.50, responseEntity.getBody().getPrice() );
    }

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_15_10() {
        LocalDateTime localDateTime = LocalDateTime.of( 2020,06,15,10,00,00 );
        FilterPrices filterPrices = new FilterPrices(35455, 1, localDateTime);
        ResponseEntity<Prices> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals( 30.50, responseEntity.getBody().getPrice() );
    }

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_16_21() {
        LocalDateTime localDateTime = LocalDateTime.of( 2020,06,16,21,00,00 );
        FilterPrices filterPrices = new FilterPrices(35455, 1, localDateTime);
        ResponseEntity<Prices> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals( 38.95, responseEntity.getBody().getPrice() );
    }

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    void testPrice_notFound() {
        LocalDateTime localDateTime = LocalDateTime.of( 2021,06,16,21,00,00 );
        FilterPrices filterPrices = new FilterPrices(36577, 2, localDateTime);
        assertThrows( RestClientException.class, () -> this.restTemplate
                .postForEntity("http://localhost:" + port + "/price", filterPrices, Prices.class) );
    }
}