package br.com.accenture.wallet.transaction.domain.event;

public class CancelledTransferEvent {

    private String transferId;

    public CancelledTransferEvent(String id) {
        this.transferId = id;
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String value) { this.transferId = value; }

}
