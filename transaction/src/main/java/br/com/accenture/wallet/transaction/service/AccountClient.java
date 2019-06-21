package br.com.accenture.wallet.transaction.service;

import br.com.accenture.wallet.transaction.domain.model.BalanceModel;
import br.com.accenture.wallet.transaction.domain.model.BalanceOperationModel;
import org.springframework.cloud.openfeign.FeignClient;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@FeignClient(value = "accountClient")
public interface AccountClient {

    @RequestMapping(method = GET, value = "${account.endpoint}/{id}", produces = APPLICATION_JSON_VALUE)
    BalanceModel getAccount(@PathVariable("id") String account);

    @RequestMapping(method = PUT, value = "${account.endpoint}", produces = APPLICATION_JSON_VALUE)
    String updateBalance(BalanceOperationModel model);

}
