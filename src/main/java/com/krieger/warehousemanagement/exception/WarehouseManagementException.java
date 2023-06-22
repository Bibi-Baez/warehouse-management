package com.krieger.warehousemanagement.exception;

import org.springframework.http.HttpStatus;

public class WarehouseManagementException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String message;

    public WarehouseManagementException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public WarehouseManagementException(HttpStatus status, String message, String extraMessage) {
        super();
        this.status = status;
        this.message = message;
        this.message = extraMessage;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
