package br.com.accenture.wallet.account.service;

import br.com.accenture.wallet.account.domain.*;
import br.com.accenture.wallet.account.repository.AccountRepository;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.ApplicationScope;
import java.net.ConnectException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import static java.util.Objects.isNull;

@Service
@ApplicationScope
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    private final CustomerClient customerClient;

    @Autowired
    public AccountService(AccountRepository repo, CustomerClient client) {
        this.accountRepository = repo;
        this.customerClient = client;
    }

    public List<AccountModel> findAll() {
        return accountRepository.findAll().stream()
            .map(AccountModel::fromEntity)
            .collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<AccountModel> create(AccountModel model) {
        return findCustomer(model.getCustomer()).thenApply(customer -> {
            if (isNull(customer)) {
                logger.warn("Customer nao encontrado com ID {}", model.getCustomer());
                return null;
            }

            logger.info("Criando uma conta para o cliente ".concat(customer.getName()));
            final Account account = accountRepository.save(model.toEntity()
                .setBalance(0.0)
                .setTransactionLimit(100.0));
            model.setId(account.getId());
            return model;
        });
    }

    public Optional<AccountModel> getById(String value) {
        final Optional<Account> account = accountRepository.findById(value);
        if (account.isEmpty()) return Optional.empty();
        return Optional.of(AccountModel.fromEntity(account.get()));
    }

    public void remove(String accountId) {
        accountRepository.deleteById(accountId);
    }

    @Async
    public CompletableFuture<CustomerModel> findCustomer(String id) {
        return Failsafe.with(retryPolicy(id))
            .getAsync(() -> customerClient.getCustomer(id))
            .exceptionally(err -> null);
    }

    @SuppressWarnings("unchecked")
    private RetryPolicy<Object> retryPolicy(final String id) {
        return new RetryPolicy<>()
            .handle(ConnectException.class, RestClientException.class)
            .withMaxRetries(5)
            .withDelay(Duration.ofMillis(750))
            .onFailedAttempt(obj -> logger.error("Fail to get info from Customer with ID ".concat(id)));
    }

    public void activate(final String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) throw new IllegalArgumentException("Account does not exist with ID.");
        final Account entity = account.get();
        if (entity.isActive()) throw new IllegalArgumentException("Account has been activated.");
        entity.setActive(true);
        accountRepository.save(entity);
    }

    public void updateBalance(final BalanceModel model) {
        Optional<Account> account = accountRepository.findById(model.getAccount());
        if (account.isEmpty()) throw new IllegalArgumentException("Account does not exist with ID.");

        final Account entity = account.get();
        if (!entity.isActive()) throw new IllegalArgumentException("Account is inactive.");

        Double currentBalance = entity.getBalance();
        if ("credit".equals(model.getOperation()))
            currentBalance = currentBalance + model.getValue();
        else if ("debit".equalsIgnoreCase(model.getOperation()))
            currentBalance = currentBalance - model.getValue();

        entity.setBalance(currentBalance);
        accountRepository.save(entity);
    }

}
