package br.com.accenture.wallet.transaction.domain.model;

import br.com.accenture.wallet.transaction.domain.Transaction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TransactionModel {
    private static DateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String id;

    private Double amount;

    private String operation;

    private String type;

    private String registered;

    public TransactionModel() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public TransactionModel withId(String value) {
        this.id = value;
        return this;
    }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public TransactionModel withAmount(Double value) {
        this.amount = value;
        return this;
    }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public TransactionModel withOperation(String value) {
        this.operation = value;
        return this;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public TransactionModel withType(String value) {
        this.type = value;
        return this;
    }

    public String getRegistered() { return registered; }
    public void setRegistered(String registered) { this.registered = registered; }
    public TransactionModel withRegistered(String value) {
        this.registered = value;
        return this;
    }

    public static TransactionModel fromEntity(Transaction entity) {
        return new TransactionModel()
            .withId(entity.getId())
            .withAmount(entity.getAmount().doubleValue())
            .withOperation(entity.getOperation().intValue() == 1 ? "Credit" : "Debit")
            .withType("Transfer")
            .withRegistered(dtFormat.format(entity.getCreated()));
    }

}
