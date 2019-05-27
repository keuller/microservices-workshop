package br.com.accenture.wallet.account.controller

import br.com.accenture.wallet.account.domain.AccountBean
import br.com.accenture.wallet.account.domain.CustomerBean
import br.com.accenture.wallet.account.service.AccountService
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/accounts")
class AccountController(val service: AccountService) {

    @GetMapping
    fun all(): List<AccountBean> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): AccountBean? {
        val account = service.getById(id)
        return if (account.isEmpty) null else account.get()
    }

    @GetMapping("/{id}/customer")
    fun getAccountCustomer(@PathVariable id: String): CustomerBean? {
        val account = service.getById(id)
        if (account.isEmpty) return null
        val customer = service.findCustomer(account.get().customer)
        return if (customer.isEmpty) return null else customer.get()
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody model: AccountBean): AccountBean {
        return service.create(model)!!
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun remove(@PathVariable id: String) {
        service.remove(id)
    }

}