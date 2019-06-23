package br.com.accenture.wallet.account.controller;

import br.com.accenture.wallet.account.domain.AccountModel;
import br.com.accenture.wallet.account.domain.BalanceModel;
import br.com.accenture.wallet.account.domain.CustomerModel;
import br.com.accenture.wallet.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
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
    public CompletableFuture<ResponseEntity<AccountModel>> create(@RequestBody @Valid AccountModel model) {
        return service.create(model)
            .thenApply(result -> {
                if (Objects.isNull(result)) return ResponseEntity.badRequest().body(null);
                try {
                    return ResponseEntity.created(new URI("/v1/accounts")).body(result);
                } catch (URISyntaxException err) {
                    err.printStackTrace();
                    return ResponseEntity.unprocessableEntity().body(null);
                }
            });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activate(@PathVariable String id) {
        try {
            service.activate(id);
            return ResponseEntity.ok("Account activated successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Valid
    @PutMapping("/balance")
    public ResponseEntity<String> updateBalance(@RequestBody BalanceModel model) {
        try {
            service.updateBalance(model);
            return ResponseEntity.ok("Balance was updated.");
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
