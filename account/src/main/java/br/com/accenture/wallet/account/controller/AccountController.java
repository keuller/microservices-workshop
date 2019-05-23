package br.com.accenture.wallet.account.controller;

import br.com.accenture.wallet.account.domain.AccountModel;
import br.com.accenture.wallet.account.domain.CustomerModel;
import br.com.accenture.wallet.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}/customer")
    public CustomerModel getAccountCustomer(@PathVariable String id) {
        Optional<AccountModel> account = service.getById(id);
        if (account.isPresent()) {
            Optional<CustomerModel> customer = service.findCustomer(account.get().getCustomer());
            return customer.get();
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountModel create(@RequestBody AccountModel model) {
        return service.create(model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

}
