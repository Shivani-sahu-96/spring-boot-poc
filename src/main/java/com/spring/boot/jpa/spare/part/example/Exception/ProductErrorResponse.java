package com.spring.boot.jpa.spare.part.example.Exception;


import lombok.Data;

@Data

public class ProductErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public ProductErrorResponse() {
    }
}
