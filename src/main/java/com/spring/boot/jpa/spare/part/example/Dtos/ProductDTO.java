package com.spring.boot.jpa.spare.part.example.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.boot.jpa.spare.part.example.Validations.NameMatch;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
//    @Pattern(regexp= "^[a-zA-Z]*$")

//    @JsonInclude(JsonInclude.Include.NON_NULL)

    int model_no;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @NameMatch
    String companyName;

    @DecimalMin(value = "100.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    Double price;

    @NotEmpty
    List<@NotNull @Valid SparePartsDto> spareParts;


    public ProductDTO(String companyName, Double price, List<SparePartsDto> spareParts) {
        this.companyName = companyName;
        this.price = price;
        this.spareParts = spareParts;
    }

    public ProductDTO() {
    }

    public ProductDTO(String lenovo, double v) {
        this.companyName = companyName;
        this.price = price;
    }

    public ProductDTO(int id, String companyName, Double price) {
        this.model_no = id;
        this.companyName = companyName;
        this.price = price;
    }
}
