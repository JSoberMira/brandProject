package com.inditex.priceMicroService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FilterPrices {
    @JsonProperty
    @NotNull
    private Integer productId;
    @JsonProperty
    @NotNull
    private Integer brandId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty
    @NotNull
    @Schema( type = "string", example = "2020-06-14 10:00")
    private LocalDateTime applicationDate;
}
