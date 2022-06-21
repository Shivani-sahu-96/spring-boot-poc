package com.spring.boot.jpa.spare.part.example;

import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Repository.ProductRepository;
import com.spring.boot.jpa.spare.part.example.Service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProductServiceTest {


    @InjectMocks
    private ProductService pService;
    @Mock
    ProductRepository pRepository;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Rollback(value = false)
    public void testSaveProduct(){
        SpareParts spareParts=new SpareParts();
        spareParts.setSparePartName("Mouse");
        spareParts.setSparePartPrice(5000.0);
        spareParts.setSparePartCategory("Computer");
        List<SpareParts> spareParts1=new ArrayList<>();
        spareParts1.add(spareParts);
        Product productSave=new Product(50,"Dell",500.0,spareParts1);
        when(pRepository.save(Mockito.any())).thenReturn(productSave);
        pService.saveProduct(productSave);
        System.out.println("jdsks");
    }

    @Test
    public void testFetchAllProduct() {
        Product entity = new Product();
        entity.setModel_no(1);
        entity.setCompanyName("Hp");
        entity.setPrice(100000.0);
        when(pRepository.findAll()).thenReturn(Arrays.asList(entity));
        List<Product> beans = pService.getAllProduct();
        assertEquals(1, beans.size());
        assertEquals(1, beans.get(0).getModel_no());
    }
    @Test
    public void testFindCompanyName() {
        Product entity = new Product();
        entity.setModel_no(1);
        entity.setCompanyName("Hp");
        entity.setPrice(10000.0);
        when(pRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(entity));
        Product bean = pService.getProductById(1);
        assertEquals(1, bean.getModel_no());
        assertEquals("Hp", bean.getCompanyName());
    }

    @Test
    public void testProductNotAvailable() {
        when(pRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
        Product product = pService.getProductById(1);
        assertEquals(null, product);
    }

    @Test
    public void testCheckCompan() {
        List<Product> product = pService.getProductByCompany("Dell");
        when(pRepository.findByCompanyName(Mockito.anyString())).thenReturn(product);
        boolean isPresent = pService.checkName("Dell");
        assertTrue(isPresent);
    }

    @Test
    public void testUpdateProduct(){

    }
}
