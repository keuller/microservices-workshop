package br.com.accenture.wallet.customer.service

import br.com.accenture.wallet.customer.domain.CustomerBean
import br.com.accenture.wallet.customer.repository.CustomerRepository
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import java.util.Objects.isNull

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class CustomerService(val repository: CustomerRepository) {

    fun create(bean: CustomerBean): CustomerBean {
        val customer = bean.toEntity()
        customer.id = UUID.randomUUID().toString()
        return bean.fromEntity(repository.save(customer))
    }

    fun remove(id: String) {
        repository.deleteById(id)
    }

    fun update(model: CustomerBean): CustomerBean? {
        val customer = repository.findById(model.id)
        if (customer.isEmpty) return null
        repository.save(model.toEntity())
        return model
    }

    fun findById(value: String): Optional<CustomerBean> {
        val customer = repository.findById(value)
        return if (customer.isEmpty) Optional.empty() else Optional.of(CustomerBean().fromEntity(customer.get()))
    }

    fun findAll(): List<CustomerBean> {
        return repository.findAll()
            .stream()
            .map { customer -> CustomerBean().fromEntity(customer) }
            .collect(Collectors.toList())
    }

    fun findByEmail(value: String?): CustomerBean? {
        if (isNull(value)) return null
        val customer = repository.findByEmail(value!!)
        return if (customer.isEmpty) null else CustomerBean().fromEntity(customer.get())
    }

}
