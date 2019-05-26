package br.com.accenture.wallet.onboarding.domain

import javax.validation.constraints.NotBlank

data class AccountBean(
    var id: String?,
    var customer: String,
    val type: String
)

data class BalanceBean(
    val customer: String,
    val account: String,
    val value: Double = 0.0
)

data class CustomerBean(var id: String?) {
    @NotBlank
    var name: String = ""

    @NotBlank
    var email: String = ""

    fun withName(value: String): CustomerBean {
        this.name = value
        return this
    }

    fun withEmail(value: String): CustomerBean {
        this.email = value
        return this
    }
}