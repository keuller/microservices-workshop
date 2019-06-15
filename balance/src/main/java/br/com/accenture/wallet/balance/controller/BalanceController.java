package br.com.accenture.wallet.balance.controller;

import br.com.accenture.wallet.balance.domain.BalanceChangeModel;
import br.com.accenture.wallet.balance.domain.BalanceModel;
import br.com.accenture.wallet.balance.service.BalanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Optional;

@Validated
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

    @Post
    public HttpResponse<BalanceModel> create(@Body @Valid BalanceModel model) {
        Optional<BalanceModel> balance = service.create(model);
        if (balance.isPresent()) return HttpResponse.created(balance.get());
        return HttpResponse.badRequest();
    }

    @Put
    public HttpResponse<String> update(@Body @Valid BalanceChangeModel model) {
        service.updateBalance(model);
        return HttpResponse.ok("Balance updated.");
    }
}
