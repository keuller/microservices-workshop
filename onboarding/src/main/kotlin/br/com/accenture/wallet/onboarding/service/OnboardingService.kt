package br.com.accenture.wallet.onboarding.service

import br.com.accenture.wallet.onboarding.domain.AccountBean
import br.com.accenture.wallet.onboarding.domain.BalanceBean
import br.com.accenture.wallet.onboarding.domain.CustomerBean
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@Scope(SCOPE_SINGLETON)
class OnboardingService {

    private val log = LoggerFactory.getLogger(OnboardingService::class.java.name)

    @Value("\${customer.service}")
    private var customerServiceUri: String = ""

    @Value("\${account.service}")
    private var accountServiceUri: String = ""

    @Value("\${balance.service}")
    private var balanceServiceUri: String = ""

    private val headers = HttpHeaders()

    private val client = RestTemplate()

    constructor() {
        headers.contentType = APPLICATION_JSON
    }

    fun create(model: CustomerBean): Boolean {
        return try {
            val customer = createCustomer(model.name, model.email)
            val account = createAccount(customer)
            createBalance(account)
            true
        } catch (ex: Exception) {
            log.error(ex.message, ex)
            false
        }
    }

    private fun createCustomer(name: String, email: String): CustomerBean {
        val model = CustomerBean("").withName(name).withEmail(email)
        val data = HttpEntity(model, headers)
        val customer = client.postForObject(customerServiceUri, data, CustomerBean::class.java)!!
        log.info("Customer was created with ID " + customer.id)
        return customer
    }

    private fun createAccount(customer: CustomerBean): AccountBean {
        val account = AccountBean(null, customer.id!!, "payment")
        val accountData = HttpEntity(account, headers)
        return client.postForObject(accountServiceUri, accountData, AccountBean::class.java)!!
    }

    private fun createBalance(account: AccountBean): BalanceBean {
        val balance = BalanceBean(account.customer, account.id!!, 10.0)
        val balanceData = HttpEntity(balance, headers)
        return client.postForObject(balanceServiceUri, balanceData, BalanceBean::class.java)!!
    }

}
