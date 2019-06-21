package br.com.accenture.wallet.onboarding.service;

import br.com.accenture.wallet.onboarding.domain.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "customerClient", url = "${customer.service}")
public interface CustomerClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerModel createCustomer(CustomerModel model);

}
