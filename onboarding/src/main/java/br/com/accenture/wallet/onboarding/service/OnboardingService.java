package br.com.accenture.wallet.onboarding.service;

import br.com.accenture.wallet.onboarding.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class OnboardingService {
    private final Logger log = LoggerFactory.getLogger(OnboardingService.class.getName());

    private CustomerClient customerClient;

    private AccountClient accountClient;

    public OnboardingService(CustomerClient customerClient, AccountClient accountClient) {
        this.customerClient = customerClient;
        this.accountClient = accountClient;
    }

    public String create(CustomerModel model) {
        try {
            final CustomerModel customer = createCustomer(model.getName(), model.getEmail());
            createAccount(customer);
            return customer.getId();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private CustomerModel createCustomer(final String name, String email) {
        final CustomerModel model = new CustomerModel().setName(name).setEmail(email);
        final CustomerModel customer = customerClient.createCustomer(model);
        log.info("Customer was created with ID {}", customer.getId());
        return customer;
    }

    private void createAccount(final CustomerModel customer) {
        final AccountModel model = new AccountModel()
            .withCustomer(customer.getId())
            .withType("payment");

        final AccountModel account = accountClient.createAccount(model);
        log.info("Account was created with ID {} for customer {}", account.getId(), customer.getId());
    }

}
