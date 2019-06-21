package br.com.accenture.wallet.transaction.domain.event;

public class TransferRequestedEvent {
    private final String transferId;
    private String sourceAccount;
    private String targetAccount;
    private Double amount;

    public TransferRequestedEvent(String transfer) {
        this.transferId = transfer;
    }

    public String getTransferId() { return transferId; }

    public String getSourceAccount() { return sourceAccount; }
    public TransferRequestedEvent withSourceAccount(String value) {
        this.sourceAccount = value;
        return this;
    }

    public String getTargetAccount() { return targetAccount; }
    public TransferRequestedEvent withTargetAccount(String value) {
        this.targetAccount = value;
        return this;
    }

    public Double getAmount() { return amount; }
    public TransferRequestedEvent withAmount(Double value) {
        this.amount = value;
        return this;
    }

}
