package br.com.accenture.wallet.transaction.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WithdrawnCommand {
    public static final String TRANSFER = "transfer";
    public static final String PAYOUT = "payout";

    @TargetAggregateIdentifier
    private String transactionId;

    private String account;

    private Double value;

    private final String type;

    public WithdrawnCommand(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    public String getAccount() { return account; }
    public WithdrawnCommand withAccount(String value) {
        this.account = value;
        return this;
    }

    public String getTransactionId() { return transactionId; }
    public WithdrawnCommand withTransactionId(String value) {
        this.transactionId = value;
        return this;
    }

    public Double getValue() { return value; }
    public WithdrawnCommand withValue(Double value) {
        this.value = value;
        return this;
    }
}
