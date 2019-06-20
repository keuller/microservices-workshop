package br.com.accenture.wallet.transaction.domain.event;

public class WithdrawnEvent {

    private final String transactionId;

    private Double value;

    public WithdrawnEvent(String transferId) {
        this.transactionId = transferId;
    }

    public String getTransactionId() { return transactionId; }

    public Double getValue() { return value; }
    public WithdrawnEvent withValue(Double value) { this.value = value; return this; }

}
