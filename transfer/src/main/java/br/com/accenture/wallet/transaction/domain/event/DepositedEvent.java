package br.com.accenture.wallet.transaction.domain.event;

public class DepositEvent {

    private String transactionId;

    public DepositEvent(String transaction) {
        this.transactionId = transaction;
    }

    public String getTransactionId() { return transactionId; }

}
