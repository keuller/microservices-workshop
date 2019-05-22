package br.com.accenture.wallet.customer.service;

import br.com.accenture.wallet.customer.domain.Customer;
import br.com.accenture.wallet.customer.domain.CustomerModel;
import br.com.accenture.wallet.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        this.repository = repo;
    }

    public CustomerModel create(final CustomerModel model) {
        Customer customer = new Customer();
        model.setId(customer.getId());
        repository.save(model.toEntity());
        return model;
    }

    public CustomerModel update(final CustomerModel model) {
        final Optional<Customer> customer = repository.findById(model.getId());
        if (customer.isEmpty()) return null;
        repository.save(model.toEntity());
        return model;
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public CustomerModel findById(String value) {
        Optional<Customer> customer = repository.findById(value);
        if (customer.isEmpty()) return null;
        return new CustomerModel("", "").fromEntity(customer.get());
    }

    public List<CustomerModel> findAll() {
        return repository.findAll()
            .stream()
            .map(customer -> new CustomerModel("", "").fromEntity(customer))
            .collect(Collectors.toList());
    }

    public CustomerModel findByEmail(String value) {
        final Optional<Customer> customer = repository.findByEmail(value);
        if (customer.isEmpty()) return null;
        return new CustomerModel("", "").fromEntity(customer.get());
    }

}
