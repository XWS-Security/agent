package com.example.agent.exceptions;

public class PdfErrorException extends RuntimeException {
    public PdfErrorException() {
        super("There has been an issue with PDF generator.");
    }
}
