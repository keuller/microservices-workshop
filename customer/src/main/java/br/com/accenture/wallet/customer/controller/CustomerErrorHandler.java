package br.com.accenture.wallet.customer.controller;

import br.com.accenture.wallet.customer.service.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Optional;

@ControllerAdvice
public class CustomerErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<FailModel> resourceNotFound(ResourceNotFoundException ex) {
        final FailModel fail = new FailModel("fail", ex.getMessage());
        return ResponseEntity.of(Optional.of(fail)).notFound().build();
    }

    class FailModel {
        public int code;
        public String type;
        public String message;

        public FailModel(String type, String msg) {
            this.type = type;
            this.message = msg;
        }

        public int getCode() { return code; }
        public String getType() { return type; }
        public String getMessage() { return message; }
    }

}
