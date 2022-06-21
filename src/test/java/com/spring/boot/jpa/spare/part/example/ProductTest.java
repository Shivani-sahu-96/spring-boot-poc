package com.spring.boot.jpa.spare.part.example;

import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Dtos.SparePartsDto;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Mapper.ProductsMapper;
import com.spring.boot.jpa.spare.part.example.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
public class ProductTest {

    @Autowired
    public ProductsMapper productsMapper;

    @Autowired
    public ProductRepository productRepository;


    @Test
    public void testCreateComputer(){
        SpareParts product=new SpareParts();
        product.setSparePartName("Speaker");
        product.setSparePartPrice(500.0);
        product.setSparePartCategory("21");
        product.setSparepart_id(121);
        SpareParts product1=new SpareParts();
        product1.setSparePartName("Cpu");
        product1.setSparePartPrice(500.0);
        product1.setSparePartCategory("21");
        product1.setSparepart_id(120);
        List<SpareParts> products=new ArrayList<>(){};
        products.add(product);
        products.add(product1);
        Product product2=new Product(13,"Touchpad3",5000.0, products);
        Product saveProduct= productRepository.save(product2);
        assertNotNull(saveProduct);
    }
    @Test
    public void testCreateComputer2(){
        SparePartsDto product=new SparePartsDto();
        product.setSparePartName("Mouse");
        product.setSparePartPrice(5000.0);
        product.setSparePartCategory("Computer");
        SparePartsDto product1=new SparePartsDto();
        product1.setSparePartName("Keyboard");
        product1.setSparePartPrice(1000.0);
        product1.setSparePartCategory("Laptop");
        List<SparePartsDto> products=new ArrayList<>(){};
        products.add(product);
        products.add(product1);
        ProductDTO product2=new ProductDTO("Mi",70000.0, products);
        Product saveProduct= productRepository.save(productsMapper.toProduct(product2));
        assertNotNull(saveProduct);
    }
    @Test
    public void testCreateComputer3(){
        SpareParts product=new SpareParts();
        product.setSparePartName("hshd");
        product.setSparePartPrice(500.0);
        product.setSparePartCategory("21");
        product.setSparepart_id(121);
        SpareParts product1=new SpareParts();
        product1.setSparePartName("hshd");
        product1.setSparePartPrice(500.0);
        product1.setSparePartCategory("21");
        product1.setSparepart_id(120);
        List<SpareParts> products=new ArrayList<>(){};
        products.add(product);
        products.add(product1);
        Product product2=new Product(13,"Touchpad3",5000.0, products);
        Product saveProduct= productRepository.save(product2);
        assertNotNull(saveProduct);
    }
    @Test
    public void testCreateComputer4(){
        SpareParts product=new SpareParts();
        product.setSparePartName("hshd");
        product.setSparePartPrice(500.0);
        product.setSparePartCategory("21");
        product.setSparepart_id(121);
        SpareParts product1=new SpareParts();
        product1.setSparePartName("hshd");
        product1.setSparePartPrice(500.0);
        product1.setSparePartCategory("21");
        product1.setSparepart_id(120);
        List<SpareParts> products=new ArrayList<>(){};
        products.add(product);
        products.add(product1);
        Product product2=new Product(13,"Touchpad3",5000.0, products);
        Product saveProduct= productRepository.save(product2);
        assertNotNull(saveProduct);
    }
    @Test
    public void testCreateComputer5(){
        SpareParts product=new SpareParts();
        product.setSparePartName("hshd");
        product.setSparePartPrice(500.0);
        product.setSparePartCategory("21");
        product.setSparepart_id(121);
        SpareParts product1=new SpareParts();
        product1.setSparePartName("hshd");
        product1.setSparePartPrice(500.0);
        product1.setSparePartCategory("21");
        product1.setSparepart_id(120);
        List<SpareParts> products=new ArrayList<>(){};
        products.add(product);
        products.add(product1);
        Product product2=new Product(13,"Touchpad3",5000.0, products);
        Product saveProduct= productRepository.save(product2);
        assertNotNull(saveProduct);
    }

    @Test
    public void testFindProductByCompanyName(){
        String name="Mi";
        List<Product> product= productRepository.findByCompanyName(name);
        assertEquals(name,productRepository.findByCompanyName(name).get(1).getCompanyName());
    }

    @Test
    public void testFindSparePartById(){

        assertEquals(1,productRepository.findById(1).get().getModel_no());
    }

    @Test
    public void testGetAllSparePart(){
        List<Product> products=productRepository.findAll();
        assertTrue(products.size()>0);
    }
    @Test
    public void testUpdateSparePart(){
        String name="Vivo";

        Product updateProduct=productRepository.findById(23).orElseThrow();
        updateProduct.setCompanyName(name);
        updateProduct.setSpareParts(productRepository.findById(23).get().getSpareParts());
        updateProduct.setPrice(productRepository.findById(23).get().getPrice());
        Product product= productRepository.save(updateProduct);

        assertNotNull(product);
        assertEquals(name,productRepository.findById(23).get().getCompanyName());


    }
    @Test
    @Rollback(value = true)
    public void testDeleteSparePart(){
        int id=18;
        boolean isIdExsist = productRepository.findById(id).isPresent();
        productRepository.deleteById(id);
        boolean isIdPresent=productRepository.findById(id).isPresent();
        assertTrue(isIdExsist);
        assertFalse(isIdPresent);

    }
}
