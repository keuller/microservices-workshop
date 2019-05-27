package br.com.accenture.wallet.balance.controller

import br.com.accenture.wallet.balance.domain.BalanceBean
import br.com.accenture.wallet.balance.service.BalanceService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/v1/balances")
class BalanceController(@Inject val service: BalanceService) {

    @Get("/{id}/account")
    fun getByAccount(id: String): HttpResponse<BalanceBean> {
        val model = service.getByAccount(id)
        return if (model.isPresent) HttpResponse.ok(model.get()) else HttpResponse.notFound()
    }

    @Post
    fun create(@Body @Valid model: BalanceBean): HttpResponse<BalanceBean> {
        val balance = service.create(model)
        return if (balance.isPresent) HttpResponse.created(balance.get()) else HttpResponse.badRequest()
    }

}
