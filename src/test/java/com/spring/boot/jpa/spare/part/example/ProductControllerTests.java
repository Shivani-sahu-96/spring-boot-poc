package com.spring.boot.jpa.spare.part.example;

import com.spring.boot.jpa.spare.part.example.Controller.ProductController;
import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Dtos.SparePartsDto;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ProductController.class)
public class ProductControllerTests {
    @LocalServerPort
    private int port;
    private static final Logger logger = LoggerFactory.getLogger(ProductControllerTests.class);

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testAddProduct1() {
        String body = "{\n" +
                "   \"companyName\":\"Mac\",\n" +
                "   \"price\":60000.0,\n" +
                "   \"spareParts\":[\n" +
                "        {\n" +
                "          \"sparePartName\": \"Hinge\",\n" +
                "          \"sparePartCategory\":\"laptop\",\n" +
                "          \"sparePartPrice\":2000.0\n" +
                "        },\n" +
                "        {\n" +
                "          \"sparePartName\": \"Battery\",\n" +
                "          \"sparePartCategory\":\"laptop\",\n" +
                "          \"sparePartPrice\":2000.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddProductNameShouldNotLengthLessThanTwo() {
        String body = " {\n" +
                "        \"companyName\": \"h\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"gd\",\n" +
                "                \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartNameShouldNotLengthLessThanTwo() {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"D\",\n" +
                "                \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartCategoryShouldNotLengthLessThanTwo() {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"Dell\",\n" +
                "                \"sparePartCategory\": \"l\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartNameShouldNotLengthGreaterThanTen() {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"Dellllllllllll\",\n" +
                "                \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartCategoryShouldNotLengthGreaterThanTen() {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"Dell\",\n" +
                "                \"sparePartCategory\": \"laptopppppp\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductNameShouldNotLengthGreaterThanTen() {
        String body = " {\n" +
                "        \"companyName\": \"Hpppppppppppp\",\n" +
                "        \"price\": 1000.0,\n" +
                "        \"spareParts\": [\n" +
                "            {\n" +
                "                \"sparePartName\": \"Dell\",\n" +
                "                \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductNegativePrice() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Company name is not available");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductSparePartPriceNegative() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": -1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductSparePartPriceNegative1() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": -1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductSparePartNameNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": -1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductSparePartCategoryNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": -1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductSparePartPriceNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": \n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 0.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductProductPriceNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": ,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\":1000.0 \n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Price integer given");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductNameNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"gd\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Company Name is null");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductNameContainsDigit() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp12\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"Mouse\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Company Name contains digit");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartNameContainsDigit() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"Mouse23\",\n" + " \"sparePartCategory\": \"laptop\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Spare part name contains digit");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartCategoryContainsDigit() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"Mouse\",\n" + " \"sparePartCategory\": \"laptop2\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Spare part name contains digit");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddSparePartCategoryNull() throws Exception {
        String body = " {\n" +
                "        \"companyName\": \"Hp\",\n" + "        \"price\": 1000.0,\n" + " \"spareParts\": [\n" +
                "            {\n" + "                \"sparePartName\": \"Mouse\",\n" + " \"sparePartCategory\": \"\",\n" +
                "                \"sparePartPrice\": 1000.0\n" + "   },\n" + " {\n" + " \"sparePartName\": \"Cooling Fan\",\n" +
                "                \"sparePartCategory\": \"Laptop\",\n" + "  \"sparePartPrice\": 1000.0\n" +
                "            }\n" + "        ]\n" + "    }";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type", al);
        Map<String, String> pathParam = new HashMap<>();
        ResponseEntity<String> response = doRestCall(createURLWithPort("/api/product/postData"), null, pathParam, body, headers, HttpMethod.POST);
        logger.info(response.toString());
        logger.info("Spare part name contains digit");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private ResponseEntity<String> doRestCall(String urlWithPort, MultiValueMap<String, String> queryParam, Map<String, String> pathParam, String body, MultiValueMap<String, String> headers, HttpMethod post) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlWithPort);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(builder.buildAndExpand(pathParam).toUri(), post, entity, String.class);

        return response;
    }

    @Test
    public void testGetProductById() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/product/fetchById/24"), HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "    \"model_no\": 24,\n" +
                "    \"companyName\": \"Mi\",\n" +
                "    \"price\": 70000.0,\n" +
                "    \"spareParts\": [\n" +
                "        {\n" +
                "            \"sparePartName\": \"Mouse\",\n" +
                "            \"sparePartCategory\": \"Computer\",\n" +
                "            \"sparePartPrice\": 5000.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"sparePartName\": \"Keyboard\",\n" +
                "            \"sparePartCategory\": \"Laptop\",\n" +
                "            \"sparePartPrice\": 1000.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetProductIdNotAvailable() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/product/fetchById/50"), HttpMethod.GET, entity, String.class);

        logger.info(response.toString());
        logger.info("Product id not available");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetProductByCategory() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/product/getCompanyName/Dell"), HttpMethod.GET, entity, String.class);

        logger.info(response.toString());
        logger.info("Product category available");
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetProductByCategoryNotAvailable() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/product/getCompanyName/mobile"), HttpMethod.GET, entity, String.class);

        logger.info(response.toString());
        logger.info("Product category not available");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
    @Test
    public void testGetProductByCategoryNull() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/product/getCompanyName/"), HttpMethod.GET, entity, String.class);

        logger.info(response.toString());
        logger.info("Product category not available");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testUpdateProductName() {
        int id = 1;
        ProductDTO productDTO = restTemplate.getForObject(createURLWithPort("/api/product/updateProduct/")+ id, ProductDTO.class);
        productDTO.setCompanyName("Dell");
        restTemplate.put(createURLWithPort("/api/product/updateProduct/") +  id, productDTO);
        ProductDTO updatedProduct = restTemplate.getForObject(createURLWithPort("/api/product/updateProduct/") + id, ProductDTO.class);
        assertNotNull(updatedProduct);
    }
    @Test
    public void testUpdateProductPrice() {
        int id = 8;
        ProductDTO productDTO = restTemplate.getForObject(createURLWithPort("/api/product/updateProduct/")+ id, ProductDTO.class);
        productDTO.setPrice(50000.0);
        restTemplate.put(createURLWithPort("/api/product/updateProduct/") +  id, productDTO);
        ProductDTO updatedProduct = restTemplate.getForObject(createURLWithPort("/api/product/updateProduct/") + id, ProductDTO.class);
        assertNotNull(updatedProduct);
    }
    @Test
    public void testUpdateSparePartName() {
        int id=50;
        SparePartsDto product=new SparePartsDto();
        product.setSparePartName("Mouse");
        List<SparePartsDto> products=new ArrayList<>(){};
        products.add(product);
        ProductDTO productDTO = new ProductDTO("Lenovo", 5000.0, products);

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<ProductDTO>(productDTO);

        // execute
        ResponseEntity<Void> responseEntity = restTemplate.exchange(createURLWithPort("/api/product/updateProduct/") +  id,
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        // verify

        logger.info(requestEntity.toString());
        logger.info("Product id not available");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

//        int status = responseEntity.getStatusCodeValue();

    }
    @Test
    public void testUpdateSparePartCategoryNotAvailable() {
        int id=50;
        SparePartsDto product=new SparePartsDto();
        product.setSparePartCategory("Computer");
        List<SparePartsDto> products=new ArrayList<>(){};
        products.add(product);
        ProductDTO productDTO = new ProductDTO("Lenovo", 5000.0, products);

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<ProductDTO>(productDTO);

        // execute
        ResponseEntity<Void> responseEntity = restTemplate.exchange(createURLWithPort("/api/product/updateProduct/") +  id,
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        // verify

        logger.info(requestEntity.toString());
        logger.info("Product id not available");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

//        int status = responseEntity.getStatusCodeValue();

    }
    @Test
    public void testUpdateSparePartCategoryAvailable() {
        int id=7;
        SparePartsDto product=new SparePartsDto();
        product.setSparePartCategory("Computer");
        List<SparePartsDto> products=new ArrayList<>(){};
        products.add(product);
        ProductDTO productDTO = new ProductDTO("Lenovo", 5000.0, products);

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<ProductDTO>(productDTO);

        // execute
        ResponseEntity<Void> responseEntity = restTemplate.exchange(createURLWithPort("/api/product/updateProduct/") +  id,
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        // verify

        logger.info(requestEntity.toString());
        logger.info("Product id not available");
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

//        int status = responseEntity.getStatusCodeValue();

    }
    @Test
    public void testUpdateSparePartPrice() {
        int id=13;
       /* SparePartsDto product=new SparePartsDto();
        product.setSparePartPrice(50000.0);
        product.set
        List<SparePartsDto> products=new ArrayList<>(){};
        products.add(product);*/
        ProductDTO productDTO = new ProductDTO("Lenovo", 5000.0);

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<ProductDTO>(productDTO);

        // execute
        ResponseEntity<Void> responseEntity = restTemplate.exchange(createURLWithPort("/api/product/updateProduct/") +  id,
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        // verify

        logger.info(requestEntity.toString());
        logger.info("Product id not available");
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

//        int status = responseEntity.getStatusCodeValue();

    }

    @Test
    public void testDeleteByIdAvailable(){
        ResponseEntity<Void> resp = restTemplate.exchange(createURLWithPort("/api/product/deleteProduct/34"), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
    }
    @Test
    public void testDeleteByIdNotAvailable(){
        ResponseEntity<Void> resp = restTemplate.exchange(createURLWithPort("/api/product/deleteProduct/2"), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        assertEquals(HttpStatus.NOT_FOUND,resp.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
