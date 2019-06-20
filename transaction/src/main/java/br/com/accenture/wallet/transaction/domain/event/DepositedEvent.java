package br.com.accenture.wallet.transaction.domain.event;

public class DepositedEvent {

    private final String transferId;

    public DepositedEvent(String value) {
        this.transferId = value;
    }

    public String getTransferId() { return transferId; }

}
