package br.com.accenture.wallet.balance

import io.micronaut.runtime.Micronaut

object BalanceApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("br.com.accenture.wallet.balance")
            .mainClass(BalanceApplication.javaClass)
            .start()
    }

}