package br.com.accenture.wallet.balance.controller;

import br.com.accenture.wallet.balance.domain.BalanceModel;
import br.com.accenture.wallet.balance.service.BalanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.Optional;

@Controller("/v1/balances")
public class BalanceController {

    private BalanceService service;

    @Inject
    public BalanceController(BalanceService service) {
        this.service = service;
    }

    @Get
    public HttpStatus all() {
        return HttpStatus.OK;
    }

    @Get("/{id}/account")
    public HttpResponse<BalanceModel> getByAccount(String id) {
        Optional<BalanceModel> model = service.getByAccount(id);
        if (model.isPresent()) return HttpResponse.ok(model.get());
        return HttpResponse.notFound();
    }

}
