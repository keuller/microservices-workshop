package br.com.accenture.wallet.onboarding.service;

import br.com.accenture.wallet.onboarding.domain.AccountModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "accountClient", url = "${account.service}")
public interface AccountClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AccountModel createAccount(AccountModel model);

}
