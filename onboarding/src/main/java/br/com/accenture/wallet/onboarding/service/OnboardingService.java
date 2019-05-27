package br.com.accenture.wallet.onboarding.service;

import br.com.accenture.wallet.onboarding.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Scope(SCOPE_SINGLETON)
public class OnboardingService {
    private final Logger log = LoggerFactory.getLogger(OnboardingService.class.getName());

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplate client;

    @Value("${customer.service}")
    private String customerServiceUri;

    @Value("${account.service}")
    private String accountServiceUri;

    @Value("${balance.service}")
    private String balanceServiceUri;

    public OnboardingService() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.client = new RestTemplateBuilder().build();
    }

    @Async
    public CompletableFuture<String> create(CustomerModel model) {
        try {
            final CustomerModel customer = createCustomer(model.getName(), model.getEmail());
            final AccountModel account = createAccount(customer);
            createBalance(account);
            return CompletableFuture.completedFuture(account.getId());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return CompletableFuture.failedFuture(ex);
        }
    }

    private CustomerModel createCustomer(final String name, String email) {
        final CustomerModel model = new CustomerModel().setName(name).setEmail(email);

        final HttpEntity<CustomerModel> data = new HttpEntity<>(model, headers);
        final CustomerModel customer = client.postForObject(customerServiceUri, data, CustomerModel.class);
        log.info("Customer was created with ID ".concat(customer.getId()));
        return customer;
    }

    private AccountModel createAccount(final CustomerModel customer) {
        final AccountModel account = new AccountModel()
            .setCustomer(customer.getId())
            .setType("payment");

        final HttpEntity<AccountModel> accountData = new HttpEntity<>(account, headers);
        return client.postForObject(accountServiceUri, accountData, AccountModel.class);
    }

    private BalanceModel createBalance(final AccountModel account) {
        final BalanceModel balance = new BalanceModel()
            .setCustomer(account.getCustomer())
            .setAccount(account.getId())
            .setValue(10.0d);

        final HttpEntity<BalanceModel> balanceData = new HttpEntity<>(balance, headers);
        return client.postForObject(balanceServiceUri, balanceData, BalanceModel.class);
    }

}
