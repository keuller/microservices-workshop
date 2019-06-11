package br.com.accenture.wallet.customer.controller;

import br.com.accenture.wallet.customer.service.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CustomerErrorHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public FailModel resourceNotFound(ResourceNotFoundException ex) {
        return new FailModel("fail", ex.getMessage());
    }

    class FailModel {
        public int code;
        public String type;
        public String message;

        public FailModel(String type, String msg) {
            this.type = type;
            this.message = msg;
        }

        public int getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }

}
