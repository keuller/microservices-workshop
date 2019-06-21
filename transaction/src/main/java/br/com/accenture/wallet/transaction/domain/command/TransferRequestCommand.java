package br.com.accenture.wallet.transaction.domain.command;

import br.com.accenture.wallet.transaction.domain.model.TransferModel;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import static java.util.Objects.isNull;

public class TransferRequestCommand {

    @TargetAggregateIdentifier
    private String transferId;

    private final String sourceAccount;

    private final String targetAccount;

    private final Double amount;

    public TransferRequestCommand(String transferId, String balanceUrl, TransferModel model) {
        if (isNull(transferId) || transferId.isBlank()) {
            throw new IllegalArgumentException("transfer ID cannot be empty");
        }

        this.transferId = transferId;
        this.sourceAccount = model.getSourceAccount();
        this.targetAccount = model.getTargetAccount();
        this.amount = model.getAmount();
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String value) { this.transferId = value; }

    public String getSourceAccount() { return sourceAccount; }

    public String getTargetAccount() { return targetAccount; }

    public Double getAmount() { return amount; }

}
