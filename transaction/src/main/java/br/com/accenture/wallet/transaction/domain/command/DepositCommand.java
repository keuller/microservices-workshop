package br.com.accenture.wallet.transaction.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DepositCommand {

    @TargetAggregateIdentifier
    private String transactionId;

    private String account;

    private Double value;

    private final String type;

    public DepositCommand(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    public String getAccount() { return account; }
    public DepositCommand withAccount(String value) {
        this.account = value;
        return this;
    }

    public String getTransactionId() { return transactionId; }
    public DepositCommand withTransactionId(String value) {
        this.transactionId = value;
        return this;
    }

    public Double getValue() { return value; }
    public DepositCommand withValue(Double value) {
        this.value = value;
        return this;
    }
}
