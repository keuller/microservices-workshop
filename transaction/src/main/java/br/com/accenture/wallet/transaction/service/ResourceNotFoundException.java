package br.com.accenture.wallet.transaction.service;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entity) {
        super(String.format("Resource '%s' cannot be found.", entity));
    }

    public ResourceNotFoundException(String entity, String id) {
        super(String.format("Resource '%s' cannot be found with '%s'.", entity, id));
    }

}
