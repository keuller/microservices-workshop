package br.com.accenture.wallet.balance.service;

import br.com.accenture.wallet.balance.domain.Balance;
import br.com.accenture.wallet.balance.domain.BalanceModel;
import br.com.accenture.wallet.balance.repository.BalanceRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class BalanceService {

    private BalanceRepository repository;

    @Inject
    public BalanceService(BalanceRepository repo) {
        this.repository = repo;
    }

    public Optional<BalanceModel> getByAccount(String accountId) {
        Optional<Balance> balance = repository.findByAccount(accountId);
        if (balance.isEmpty()) return Optional.empty();
        return Optional.of(new BalanceModel().fromEntity(balance.get()));
    }

    public Optional<BalanceModel> create(BalanceModel model) {
        model.setValue(0.0d);
        Optional<Balance> balance = repository.save(model.toEntity());
        if (balance.isEmpty()) Optional.empty();
        return Optional.of(new BalanceModel().fromEntity(balance.get()));
    }

}
