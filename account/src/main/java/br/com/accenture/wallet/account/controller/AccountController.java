package br.com.accenture.wallet.account.controller;

import br.com.accenture.wallet.account.domain.AccountModel;
import br.com.accenture.wallet.account.domain.CustomerModel;
import br.com.accenture.wallet.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountModel> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AccountModel getById(@PathVariable String id) {
        Optional<AccountModel> account = service.getById(id);
        if (account.isEmpty()) return null;
        return account.get();
    }

    @Async
    @GetMapping("/{id}/customer")
    public CompletableFuture<CustomerModel> getAccountCustomer(@PathVariable String id) {
        Optional<AccountModel> account = service.getById(id);
        if (account.isPresent()) {
            return service.findCustomer(account.get().getCustomer());
        }
        return null;
    }

    @Async
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<AccountModel> create(@RequestBody AccountModel model) {
        return service.create(model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

}
