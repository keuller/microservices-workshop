package br.com.accenture.wallet.customer.controller

import br.com.accenture.wallet.customer.domain.CustomerBean
import br.com.accenture.wallet.customer.service.CustomerService
import br.com.accenture.wallet.customer.service.ResourceNotFoundException
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

@RestController
@RequestMapping("/v1/customers")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun all(): List<CustomerBean> {
        return service.findAll()
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody @Valid bean: CustomerBean): CustomerBean {
        return service.create(bean)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @Valid @RequestBody model: CustomerBean): CustomerBean? {
        model.id = id
        return service.update(model)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): CustomerBean? {
        val bean = service.findById(id)
        return if (bean.isEmpty) throw ResourceNotFoundException("Customer") else bean.get()
    }

    @GetMapping("/find")
    fun findByEmail(@RequestParam("email") email: String?): CustomerBean? {
        return service.findByEmail(email)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun remove(@PathVariable id: String) {
        service.remove(id)
    }

}
