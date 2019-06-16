package br.com.accenture.wallet.balance.repository;

import br.com.accenture.wallet.balance.domain.Balance;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Singleton
public class BalanceRepository {
    private final String hqlFindByAccount = "SELECT b FROM Balance b WHERE b.accountId = :account";

    @PersistenceContext
    private EntityManager manager;

    public BalanceRepository(@CurrentSession EntityManager manager) {
        this.manager = manager;
    }

    @Transactional(readOnly = true)
    public Optional<Balance> findByAccount(String value) {
        TypedQuery<Balance> query = manager.createQuery(hqlFindByAccount, Balance.class);
        try {
            query.setParameter("account", value);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException|NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    public List<Balance> findTop5() {
        TypedQuery<Balance> query = manager.createQuery("SELECT b FROM Balance b ORDER BY b.accountId", Balance.class)
            .setMaxResults(5);
        return query.getResultList();
    }

    @Transactional
    public Optional<Balance> save(Balance bean) {
        try {
            manager.persist(bean);
            return Optional.of(bean);
        } catch (EntityExistsException ex) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Balance> update(Balance bean) {
        try {
            bean.setLastUpdate(new Date());
            manager.merge(bean);
            return Optional.of(bean);
        } catch (EntityExistsException ex) {
            return Optional.empty();
        }
    }

}
