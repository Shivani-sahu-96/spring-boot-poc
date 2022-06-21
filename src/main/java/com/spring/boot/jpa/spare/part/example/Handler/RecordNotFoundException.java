package com.spring.boot.jpa.spare.part.example.Handler;

public class RecordNotFoundException extends RuntimeException
{
    public RecordNotFoundException(String exception) {
        super(exception);
    }
}