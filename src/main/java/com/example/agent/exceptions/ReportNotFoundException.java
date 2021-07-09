package com.example.agent.exceptions;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException() {
        super("Report could not be found.");
    }
}
