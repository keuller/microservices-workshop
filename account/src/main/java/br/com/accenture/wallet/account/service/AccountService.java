package br.com.accenture.wallet.account.service;

import br.com.accenture.wallet.account.domain.*;
import br.com.accenture.wallet.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.ApplicationScope;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Service
@ApplicationScope
@Scope(SCOPE_SINGLETON)
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Value("${customer.service}")
    private String customerServiceUrl;

    private final RestTemplate customerClient = new RestTemplate();

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository repo) {
        this.accountRepository = repo;
    }

    public List<AccountModel> findAll() {
        return accountRepository.findAll()
            .stream()
            .map(entity -> new AccountModel().fromEntity(entity))
            .collect(Collectors.toList());
    }

    public AccountModel create(AccountModel model) {
        Optional<CustomerModel> customer = findCustomer(model.getCustomer());
        if (customer.isEmpty()) return null;
        logger.info("Criando uma conta para o cliente ".concat(customer.get().getName()));
        final Account account = accountRepository.save(model.toEntity());
        model.setId(account.getId());
        return model;
    }

    public Optional<AccountModel> getById(String value) {
        final Optional<Account> account = accountRepository.findById(value);
        if (account.isEmpty()) return Optional.empty();
        return Optional.of(new AccountModel().fromEntity(account.get()));
    }

    public void remove(String accountId) {
        accountRepository.deleteById(accountId);
    }

    public Optional<CustomerModel> findCustomer(String id) {
        try {
            CustomerModel customer = customerClient
                .getForEntity(customerServiceUrl.concat(id), CustomerModel.class)
                .getBody();
            return Optional.of(customer);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Optional.empty();
        }
    }

}
