package br.com.accenture.wallet.customer.repository

import br.com.accenture.wallet.customer.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    fun findByEmail(@Param("email") email: String): Optional<Customer>

}
