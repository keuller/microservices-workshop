package br.com.accenture.wallet.account.repository

import br.com.accenture.wallet.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, String>