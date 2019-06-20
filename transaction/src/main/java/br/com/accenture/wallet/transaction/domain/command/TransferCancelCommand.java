package br.com.accenture.wallet.transaction.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransferCancelCommand {

    @TargetAggregateIdentifier
    private String transferId;

    private Throwable cause;

    public TransferCancelCommand(String id) {
        this.transferId = id;
    }

    public String getTransferId() { return transferId; }

    public Throwable getCause() { return cause; }
    public TransferCancelCommand withCause(Throwable value) {
        this.cause = value;
        return this;
    }
}
