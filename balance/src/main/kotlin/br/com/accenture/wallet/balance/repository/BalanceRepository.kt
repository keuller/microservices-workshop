package br.com.accenture.wallet.balance.repository

import br.com.accenture.wallet.balance.domain.Balance
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import java.util.*
import javax.inject.Named
import javax.inject.Singleton
import javax.persistence.*

@Named
@Singleton
// open class must be declared because Hibernate proxy
open class BalanceRepository(@CurrentSession @PersistenceContext var manager: EntityManager) {

    private val findByAccount = "SELECT b FROM Balance b WHERE b.account = :account"

    @Transactional(readOnly = true)
    open fun findByAccount(value: String): Optional<Balance> {
        val query = manager.createQuery(findByAccount, Balance::class.java)
        return try {
            query.setParameter("account", value)
            Optional.of(query.singleResult)
        } catch (ex: NoResultException) {
            Optional.empty()
        } catch (ex: NonUniqueResultException) {
            Optional.empty()
        }
    }

    @Transactional
    open fun save(bean: Balance): Optional<Balance> {
        return try {
            manager.persist(bean)
            Optional.of(bean)
        } catch (ex: EntityExistsException) {
            Optional.empty()
        }
    }
}