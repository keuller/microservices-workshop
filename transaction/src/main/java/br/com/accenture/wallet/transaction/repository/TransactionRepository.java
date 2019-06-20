package br.com.accenture.wallet.transaction.repository;

import br.com.accenture.wallet.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("SELECT t FROM Transaction t WHERE t.account=:account AND t.created >= :start AND t.created <= :end")
    List<Transaction> findTransactions(@Param("account") String account,
                                       @Param("start") Date start,
                                       @Param("end") Date end);

}
