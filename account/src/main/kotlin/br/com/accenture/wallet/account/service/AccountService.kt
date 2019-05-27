package br.com.accenture.wallet.account.service

import br.com.accenture.wallet.account.domain.AccountBean
import br.com.accenture.wallet.account.domain.CustomerBean
import br.com.accenture.wallet.account.repository.AccountRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.stream.Collectors

@Service
@Scope(SCOPE_SINGLETON)
class AccountService(val repository: AccountRepository) {
    private val logger = LoggerFactory.getLogger(AccountService::class.java)

    @Value("\${customer.service}")
    private val customerServiceUrl: String? = null

    private val customerClient = RestTemplate()

    fun findAll(): List<AccountBean> {
        return repository.run {
            findAll()
                .stream()
                .map { entity -> AccountBean(null, false).fromEntity(entity) }
                .collect(Collectors.toList())
        }
    }

    fun create(model: AccountBean): AccountBean? {
        val customer = findCustomer(model.customer)
        if (customer.isEmpty()) return null
        logger.info("Criando uma conta para o cliente " + customer.get().name)
        val account = repository.save(model.toEntity())
        model.id = account.id
        return model
    }

    fun remove(accountId: String) {
        repository.deleteById(accountId)
    }

    fun getById(value: String): Optional<AccountBean> {
        val account = repository.findById(value)
        return if (account.isEmpty) Optional.empty() else Optional.of(AccountBean(null, false).fromEntity(account.get()))
    }

    fun findCustomer(id: String): Optional<CustomerBean> {
        return try {
            val customer = customerClient
                .getForEntity(customerServiceUrl + id, CustomerBean::class.java)
                .body
            Optional.of(customer!!)
        } catch (ex: Exception) {
            logger.error(ex.message)
            Optional.empty()
        }

    }
}
