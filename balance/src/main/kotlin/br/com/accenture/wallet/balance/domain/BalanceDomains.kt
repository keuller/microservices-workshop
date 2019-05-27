package br.com.accenture.wallet.balance.domain

import java.text.SimpleDateFormat
import java.util.*
import javax.validation.constraints.NotBlank

data class BalanceBean(var value: Double) {
    private val df = SimpleDateFormat("dd/MM/yyyy")

    var id: String? = null

    @NotBlank
    var customer: String = ""

    @NotBlank
    var account: String = ""

    var lastUpdate: String = ""

    fun fromEntity(bean: Balance): BalanceBean {
        this.id = bean.id
        this.account = bean.account
        this.customer = bean.customer
        this.value = bean.value
        this.lastUpdate = toLastUpdate(bean.lastUpdate)
        return this
    }

    fun toEntity(): Balance {
        val balance = Balance(value)
        if (Objects.nonNull(id) && "" != id) balance.id = id
        balance.account = account
        balance.customer = customer
        return balance
    }

    private fun toLastUpdate(value: Date?): String {
        if (Objects.isNull(value)) return ""
        return df.format(value)
    }
}