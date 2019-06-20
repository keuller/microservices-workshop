package br.com.accenture.wallet.transaction.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WithdrawalCommand {

    @TargetAggregateIdentifier
    private String transactionId;

    private String account;

    private Double value;

    private final String type;

    public WithdrawalCommand(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    public String getAccount() { return account; }
    public WithdrawalCommand withAccount(String value) {
        this.account = value;
        return this;
    }

    public String getTransactionId() { return transactionId; }
    public WithdrawalCommand withTransactionId(String value) {
        this.transactionId = value;
        return this;
    }

    public Double getValue() { return value; }
    public WithdrawalCommand withValue(Double value) {
        this.value = value;
        return this;
    }
}
