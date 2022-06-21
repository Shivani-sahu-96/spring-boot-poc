package com.spring.boot.jpa.spare.part.example.Controller;


import com.spring.boot.jpa.spare.part.example.Dtos.SparePartsDto;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Mapper.SparePartMapper;
import com.spring.boot.jpa.spare.part.example.Service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/spare_parts")
public class SparePartController {

    @Autowired
    private SparePartService sparePartService;


    private SparePartMapper sparePartMapper;


    @PostMapping("/postSparePart")
    public ResponseEntity<String> saveSparePart(@Valid @RequestBody SparePartsDto sparePartsDto){
        sparePartService.saveSpareParts(sparePartMapper.toSparePart(sparePartsDto));
        return new ResponseEntity<String>("SpareParts inserted successfully ", HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<SparePartsDto>> getAllProduct(){
        return ResponseEntity.ok(sparePartMapper.toSparePartDTOs(sparePartService.getAllSpareParts()));

    }


    @GetMapping("/fetchById/{product_id}")
    public ResponseEntity<SparePartsDto> getProductById(@PathVariable("product_id") int id){
        SpareParts spareParts=sparePartService.getSparePartById(id);
        return new ResponseEntity<SparePartsDto>(sparePartMapper.toSparePartDTO(spareParts),HttpStatus.OK);
    }
    @GetMapping("/category/{product_category}")
    public ResponseEntity<List<SparePartsDto>> getProductByCategory(@Valid @PathVariable("product_category") String category){
        List<SpareParts> spareParts= sparePartService.getSparePartByCategory(category);
        return ResponseEntity.ok(sparePartMapper.toSparePartDTOs(spareParts));
    }

    @PutMapping("{product_id}")
    public ResponseEntity<SparePartsDto> updateProduct(@PathVariable("product_id") int id,
                                                       @Valid @RequestBody SparePartsDto sparePartsDto){
        SpareParts spareParts = sparePartMapper.toSparePart(sparePartsDto);
        spareParts.setSparepart_id(id);
        sparePartService.saveSpareParts(spareParts);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sparePartsDto);
    }


    @DeleteMapping("{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("product_id") int id)
    {
        sparePartService.deleteSpareParts(id);
        return new ResponseEntity<String>("Spare-part Deleted Successfully",HttpStatus.OK);
    }
}
