package br.com.accenture.wallet.balance.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "balances")
data class Balance(var value: Double) {

    @Id
    @Column(name = "balance_id")
    var id: String? = null

    @Column(name = "account_id")
    var account: String = ""

    @Column(name = "customer_id")
    var customer: String = ""

    var lastTrasaction: String? = null

    var lastUpdate: Date = Date()

    constructor() : this(0.00) {} // must be declared, because of Hibernate

}
