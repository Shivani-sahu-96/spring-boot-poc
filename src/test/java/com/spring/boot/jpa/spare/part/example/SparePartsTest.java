package com.spring.boot.jpa.spare.part.example;


import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Repository.SparePartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SparePartsTest {

    @Autowired
    private SparePartRepository repository;

    @Test
    @Rollback(value = false)
    public void testCreateSparePart(){
        SpareParts product=new SpareParts(2,"Touchpad2","Desktop",5000.0);
        SpareParts saveProduct= repository.save(product);
        assertNotNull(saveProduct);
//        assert

    }

    @Test
    public void testFindSparePartByCategory(){
        String name="laptop";
        List<SpareParts> product= repository.findBysparePartCategory(name);
        assertEquals(name,repository.findBysparePartCategory(name).get(0).getSparePartCategory());
    }

    @Test
    public void testFindSparePartById(){

        assertEquals(Integer.parseInt("1"),repository.findById(Integer.parseInt("1")).get().getSparepart_id());
    }

    @Test
    @Rollback(value = false)
    public void testGetAllSparePart(){
        List<SpareParts> products=repository.findAll();
        assertTrue(products.size()>0);
    }

    @Test
    public void testUpdateSparePart(){
        String name="Keyboard";

        SpareParts updateProduct=repository.findById(Integer.parseInt("8")).orElseThrow();
        updateProduct.setSparePartName(name);
        updateProduct.setSparePartCategory(repository.findById(Integer.parseInt("8")).get().getSparePartCategory());
        updateProduct.setSparePartPrice(repository.findById(Integer.parseInt("8")).get().getSparePartPrice());
        SpareParts product= repository.save(updateProduct);

        assertNotNull(product);
        assertEquals(name,repository.findById(Integer.parseInt("8")).get().getSparePartName());


    }

    @Test
    public void testDeleteSparePart(){
        int id=8;
        boolean isIdExsist=repository.findById(id).isPresent();
        repository.deleteById(id);
        boolean isIdPresent=repository.findById(id).isPresent();
        assertTrue(isIdExsist);
        assertFalse(isIdPresent);

    }
}
