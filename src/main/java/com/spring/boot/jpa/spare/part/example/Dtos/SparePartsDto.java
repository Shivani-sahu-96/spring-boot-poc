package com.spring.boot.jpa.spare.part.example.Dtos;

import com.spring.boot.jpa.spare.part.example.Validations.NameMatch;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;


@Data
public class SparePartsDto {

    @NameMatch
    private String sparePartName;

    @NameMatch
    private String sparePartCategory;

    @DecimalMin(value = "100.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private Double sparePartPrice;
}
