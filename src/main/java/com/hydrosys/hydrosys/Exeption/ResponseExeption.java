package com.hydrosys.hydrosys.Exeption;

import java.util.Date;
import java.util.Map;

public class ResponseExeption {
    private Date timestamp;
    private String message;
    private String details;
    // SE AGREGO ATT NUEVO PARA EXCEPTIONS
    private Map<String, String> errorsValidation ;

    // Constructor para excepciones generales
    public ResponseExeption(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ResponseExeption(Date timestamp, String message, String details, Map<String, String> errorsValidation) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorsValidation = errorsValidation;
    }

    public Map<String, String> getErrorsValidation() {
        return errorsValidation;
    }

    public void setErrorsValidation(Map<String, String> errorsValidation) {
        this.errorsValidation = errorsValidation;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

