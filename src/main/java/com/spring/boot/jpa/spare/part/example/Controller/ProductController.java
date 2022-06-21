package com.spring.boot.jpa.spare.part.example.Controller;

import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Handler.ResponseMessage;
import com.spring.boot.jpa.spare.part.example.Helper.CSVHelper;
import com.spring.boot.jpa.spare.part.example.Mapper.ProductsMapper;
import com.spring.boot.jpa.spare.part.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductsMapper productMapper;

/*
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }
*/
    @PostMapping("/postData")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductDTO productDTO){

        Product product=productService.saveProduct(productMapper.toProduct(productDTO));
        logger.info("Added:: " + product);
        return new ResponseEntity<String>("Product Inserted Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllData")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        return ResponseEntity.ok(productMapper.toProductDTOs(productService.getAllProduct()));
    }


    @GetMapping("/fetchById/{model_no}")
    public ResponseEntity<ProductDTO> getProductById(@Valid @PathVariable("model_no") int id){
        Product product=productService.getProductById(id);
        if (product == null) {
            logger.info("Product with id " + id + " does not exists");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Product:: " + product);

        return ResponseEntity.ok(productMapper.toProductDTO(product));
    }
    @GetMapping("/getCompanyName/{company_name}")
    public ResponseEntity<List<ProductDTO>> getProductByCompany(@Valid @PathVariable("company_name") String category){
        logger.info("Fetching Product By Name");
        if (!category.matches("[a-zA-Z]+")){
        logger.debug("Company name is not String");
        }

        List<Product> product=productService.getProductByCompany(category);

        if (product.size() == 0) {
            logger.info("Product with id " + category + " does not exists");
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Product:: " + product);
        return ResponseEntity.ok(productMapper.toProductDTOs(product));
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                productService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
    }


    @PutMapping("/updateProduct/{model_no}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable("model_no") int  id,
                                                  @RequestBody ProductDTO productDTO){
        Product productExisting = productService.getProductById(id);
        if (productExisting==null){
            logger.debug("Product with id " + productExisting.getModel_no() + " does not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productDTO);
        }
        else
        {
            Product product1 = productMapper.toProduct(productDTO);
            productService.updateProduct(product1,id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTO);

        }

    }


    @DeleteMapping("/deleteProduct/{model_no}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable("model_no") int id)
    {
        logger.info("Deleting product"+id);
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Spare-part Deleted Successfully",HttpStatus.OK);
    }
}
