package br.com.accenture.wallet.transaction.service;

import br.com.accenture.wallet.transaction.domain.model.BalanceModel;
import br.com.accenture.wallet.transaction.domain.model.BalanceOperationModel;
import org.springframework.cloud.openfeign.FeignClient;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@FeignClient(value = "balances")
public interface BalanceClient {

    @RequestMapping(method = GET, value = "${balance.endpoint}/{id}/account")
    BalanceModel getBalanceAccount(@PathVariable("id") String account);

    @RequestMapping(method = PUT, value = "${balance.endpoint}", consumes = APPLICATION_JSON_VALUE)
    String updateBalance(BalanceOperationModel model);

}
