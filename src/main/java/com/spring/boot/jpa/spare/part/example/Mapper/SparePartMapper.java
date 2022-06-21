package com.spring.boot.jpa.spare.part.example.Mapper;

import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Dtos.SparePartsDto;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SparePartMapper {
    SparePartsDto toSparePartDTO(SpareParts spareParts);

    List<SparePartsDto> toSparePartDTOs(List<SpareParts> spareParts);

    SpareParts toSparePart(SparePartsDto sparePartsDto);
}
