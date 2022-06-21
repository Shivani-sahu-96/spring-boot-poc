package com.spring.boot.jpa.spare.part.example;

import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Repository.ProductRepository;
import com.spring.boot.jpa.spare.part.example.Repository.SparePartRepository;
import com.spring.boot.jpa.spare.part.example.Service.ProductService;
import com.spring.boot.jpa.spare.part.example.Service.SparePartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class SparePartSeviceTest {

    @InjectMocks
    private SparePartService pService;
    @Mock
    SparePartRepository pRepository;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Rollback(value = false)
    public void testSaveSparePart(){
        SpareParts spareParts=new SpareParts();
        spareParts.setSparePartName("Mouse");
        spareParts.setSparePartPrice(5000.0);
        spareParts.setSparePartCategory("Computer");
        when(pRepository.save(Mockito.any())).thenReturn(spareParts);
        pService.saveSpareParts(spareParts);
        System.out.println("jdsks");
    }
    @Test
    public void testFetchAllSparePart() {
        SpareParts entity = new SpareParts();
        entity.setSparepart_id(1);
        entity.setSparePartName("TouchPad");
        entity.setSparePartPrice(5000.0);
        entity.setSparePartCategory("laptop");
        when(pRepository.findAll()).thenReturn(Arrays.asList(entity));
        List<SpareParts> beans = pService.getAllSpareParts();
        assertEquals(1, beans.size());
        assertEquals(1, beans.get(0).getSparepart_id());
    }

    @Test
    public void testFetchAllProduct() {
        SpareParts entity = new SpareParts();
        entity.setSparepart_id(1);
        entity.setSparePartName("TouchPad");
        entity.setSparePartPrice(100000.0);
        entity.setSparePartCategory("Laptop");
        when(pRepository.findAll()).thenReturn(Arrays.asList(entity));
        List<SpareParts> beans = pService.getAllSpareParts();
        assertEquals(1, beans.size());
        assertEquals(1, beans.get(0).getSparepart_id());
    }
    @Test
    public void testSparePartNotAvailable() {
        when(pRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
        SpareParts spareParts= pService.getSparePartById(2);
        assertEquals(null, spareParts);
    }

    @Test
    public void testCheckCategory() {
        List<SpareParts> spareParts = pService.getSparePartByCategory("Computer");
        when(pRepository.findBysparePartCategory(Mockito.anyString())).thenReturn(spareParts);
        boolean isPresent = pService.checkName("Computer");
        assertTrue(isPresent);
    }


}
