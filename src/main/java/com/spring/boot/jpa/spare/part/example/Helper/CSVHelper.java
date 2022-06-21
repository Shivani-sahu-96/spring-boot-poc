package com.spring.boot.jpa.spare.part.example.Helper;

import com.spring.boot.jpa.spare.part.example.Dtos.ProductDTO;
import com.spring.boot.jpa.spare.part.example.Entity.Product;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "id", "companyName", "price"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<Product> csvToProduct(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Product> productList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Product product = new Product(
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("companyName"),
                        Double.parseDouble(csvRecord.get("price"))
                );

                productList.add(product);
            }

            return productList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

/*
    public static ByteArrayInputStream productToCSV(List<ProductDTO> ProductDTOList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (ProductDTO productDTO : ProductDTOList) {
                List<String> data = Arrays.asList(
                        String.valueOf(productDTO.getModel_no()),
                        productDTO.getCompanyName(),
                        productDTO.getPrice());

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
*/
}
