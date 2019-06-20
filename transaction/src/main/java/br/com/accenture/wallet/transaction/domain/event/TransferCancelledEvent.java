package br.com.accenture.wallet.transaction.domain.event;

import java.util.Objects;

public class TransferCancelledEvent {

    private final String transferId;

    private final String cause;

    public TransferCancelledEvent(String id, Throwable cause) {
        this.transferId = id;
        this.cause = Objects.nonNull(cause) ? cause.getMessage() : "Unknown cause of failure.";
    }

    public String getTransferId() { return transferId; }

    public String getCause() { return cause; }
}
