package balance.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/v1/balances")
public class BalanceController {

    @Get
    public HttpStatus all() {
        return HttpStatus.OK;
    }

}
