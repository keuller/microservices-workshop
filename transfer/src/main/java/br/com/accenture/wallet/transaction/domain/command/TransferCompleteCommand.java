package br.com.accenture.wallet.transaction.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransferCompleteCommand {

    @TargetAggregateIdentifier
    private final String transferId;

    public TransferCompleteCommand(String value) {
        this.transferId = value;
    }

    public String getTransferId() { return transferId; }

}
