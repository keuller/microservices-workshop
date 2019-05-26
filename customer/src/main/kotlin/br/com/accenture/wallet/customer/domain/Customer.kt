package br.com.accenture.wallet.customer.domain

import java.util.Date
import java.util.Objects.isNull
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customers")
data class Customer(val name: String) {

    @Id
    @Column(name="customer_id")
    var id: String = UUID.randomUUID().toString()

    var email: String = ""

    var gender: Int? = 0

    var birthday: Date? = null

    override fun equals(other: Any?): Boolean {
        if (isNull(other)) return false
        if (this == other) return true
        if (other !is Customer) return false
        val bean = other as Customer
        return bean.id == this.id
    }

}