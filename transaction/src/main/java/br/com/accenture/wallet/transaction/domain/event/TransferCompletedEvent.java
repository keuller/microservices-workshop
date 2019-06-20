package br.com.accenture.wallet.transaction.domain.event;

public class TransferCompletedEvent {

    private final String transferId;

    public TransferCompletedEvent(String value) {
        this.transferId = value;
    }

    public String getTransferId() { return transferId; }

}
