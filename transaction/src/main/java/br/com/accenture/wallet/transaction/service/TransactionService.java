package br.com.accenture.wallet.transaction.service;

import br.com.accenture.wallet.transaction.domain.OperationType;
import br.com.accenture.wallet.transaction.domain.model.BalanceOperationModel;
import br.com.accenture.wallet.transaction.domain.Transaction;
import br.com.accenture.wallet.transaction.domain.model.TransactionModel;
import br.com.accenture.wallet.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repo) {
        this.repository = repo;
    }

    public Transaction registerTransaction(final BalanceOperationModel model) {
        final Transaction transaction = new Transaction()
            .setAccount(model.getAccount())
            .setType(1) // transfer - TODO implement other types
            .setOperation(operationValue(model.getOperation())) // debit
            .setAmount(new BigDecimal(model.getValue()));

        return repository.save(transaction);
    }

    public List<TransactionModel> findTransactions(String account) {
        return repository.findTransactions(account, getStartDate(), getEndDate()).stream()
            .map(TransactionModel::fromEntity)
            .collect(Collectors.toList());
    }

    private Integer operationValue(String operation) {
        return (OperationType.CREDIT.equals(operation)) ? 1 : 2;
    }

    private Date getStartDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        return cal.getTime();
    }

    private Date getEndDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        return cal.getTime();
    }
}
