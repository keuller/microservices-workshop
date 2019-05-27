package br.com.accenture.wallet.balance.service

import br.com.accenture.wallet.balance.domain.BalanceBean
import br.com.accenture.wallet.balance.repository.BalanceRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Named
@Singleton
class BalanceService(@Inject val repository: BalanceRepository) {

    fun getByAccount(accountId: String): Optional<BalanceBean> {
        val balance = repository.findByAccount(accountId)
        return if (balance.isEmpty) Optional.empty() else Optional.of(BalanceBean(0.0).fromEntity(balance.get()))
    }

    fun create(model: BalanceBean): Optional<BalanceBean> {
        val balance = repository.save(model.toEntity())
        return if (balance.isEmpty) Optional.empty() else Optional.of(BalanceBean(0.0).fromEntity(balance.get()))
    }

}