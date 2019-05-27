package br.com.accenture.wallet.account.domain

import java.util.Objects.nonNull

data class AccountBean(var id: String?, var active: Boolean = false) {
    private val PAYMENT = "payment"
    private val ESCROW = "escrow"

    var customer: String = ""

    var type: String = ""

    fun fromEntity(bean: Account): AccountBean {
        val account = AccountBean(bean.id, bean.active)
        account.customer = bean.customer
        account.type = toType(bean.type)
        return account
    }

    fun toEntity(): Account {
        val account = Account(this.customer)
        if (nonNull(id) && "" != id) account.id = id!!
        if (nonNull(customer) && "" != customer) account.customer = customer
        if (nonNull(type) && "" != type) account.type = toType(type)
        return account
    }

    private fun toType(value: String?): Int? {
        return when (value) {
            PAYMENT -> 1
            ESCROW -> 2
            else -> null
        }
    }

    private fun toType(value: Int?): String {
        return when (value) {
            1 -> PAYMENT
            2 -> ESCROW
            else -> ""
        }
    }
}

data class CustomerBean(
    var id: String,
    var name: String
)