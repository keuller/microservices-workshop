package br.com.accenture.wallet.account.service;

import br.com.accenture.wallet.account.domain.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customerClient", url = "${customer.service}")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerModel getCustomer(@PathVariable  String id);

}
