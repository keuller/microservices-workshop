package br.com.accenture.wallet.customer.service

class ResourceNotFoundException : RuntimeException {

    constructor(entity: String) : super(String.format("Resource '%s' cannot be found.", entity))

    constructor(entity: String, id: String) : super(String.format("Resource '%s' cannot be found with '%s'.", entity, id))

}
