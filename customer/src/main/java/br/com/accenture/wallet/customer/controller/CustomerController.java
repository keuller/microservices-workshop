package br.com.accenture.wallet.customer.controller;

import br.com.accenture.wallet.customer.domain.CustomerModel;
import br.com.accenture.wallet.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerModel create(@Valid @RequestBody CustomerModel model) {
        return service.create(model);
    }

    @GetMapping("/{id}")
    public CustomerModel findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/find")
    public CustomerModel findByEmail(@RequestParam("email") String email) {
        if (!"".equals(email)) {
            return service.findByEmail(email);
        }
        return null;
    }

    @PutMapping("/{id}")
    public CustomerModel update(@PathVariable String id, @Valid @RequestBody CustomerModel model) {
        model.setId(id);
        return service.update(model);
    }

    @GetMapping
    public List<CustomerModel> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }
}
