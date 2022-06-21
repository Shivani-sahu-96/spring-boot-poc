package com.spring.boot.jpa.spare.part.example.Service;

import com.spring.boot.jpa.spare.part.example.Controller.ProductController;
import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Exception.ProductNotFoundException;
import com.spring.boot.jpa.spare.part.example.Helper.CSVHelper;
import com.spring.boot.jpa.spare.part.example.Repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public ProductService() {
    }

/*
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
*/

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductByCompany(String name) {
        return productRepository.findByCompanyName(name);

    }
    public void save(MultipartFile file) {
        try {
            List<Product> product = CSVHelper.csvToProduct(file.getInputStream());
            productRepository.saveAll(product);
        }  catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());

        }
    }


    public Product getProductById(int id) {
        //.orElseThrow(()-> new ProductNotFoundException("Item Not Found : "+id))
        boolean productPresent=productRepository.findById(id).isPresent();
        if (productPresent) {
            Product product = productRepository.findById(id).orElseThrow();
            logger.error("this product id is not available");
            return product;
        }
        else {
            return null;
        }

    }

    public Product updateProduct(Product product, int id) {
        Product updateProduct =productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Item Not Found : "+id));
        updateProduct.setCompanyName(product.getCompanyName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setSpareParts(product.getSpareParts());
        productRepository.save(updateProduct);
        return updateProduct;
    }

    public void deleteProduct(int id) {
        productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Item Not Found : "+id));
        productRepository.deleteById(id);
    }
    public boolean check(int id) {
        if (getProductById(id) == null) {
            return false;
        }
        return true;
    }
    public boolean checkName(String name) {
        if (getProductByCompany(name) == null) {
            return false;
        }
        return true;
    }
}
