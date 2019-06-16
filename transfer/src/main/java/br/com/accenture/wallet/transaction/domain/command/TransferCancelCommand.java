package br.com.accenture.wallet.transaction.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransferCancelCommand {

    @TargetAggregateIdentifier
    private String transferId;

    public TransferCancelCommand(String id) {
        this.transferId = id;
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String value) { this.transferId = value; }

}
