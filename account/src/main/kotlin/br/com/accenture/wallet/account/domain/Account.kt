package br.com.accenture.wallet.account.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class Account(@Column(name="customer_id") var customer: String) {

    @Id
    @Column(name="account_id")
    var id: String = UUID.randomUUID().toString()

    var type: Int? = null

    var active: Boolean = false

    var createdAt: Date = Date()

}