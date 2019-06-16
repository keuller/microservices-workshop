package br.com.accenture.wallet.transaction.domain.command;

import br.com.accenture.wallet.transaction.domain.model.TransferModel;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import static java.util.Objects.isNull;

public class RequestTransferCommand {

    @TargetAggregateIdentifier
    private String transferId;

    private final String sourceAccount;

    private final String targetAccount;

    private final Double amount;

    private final String balanceUrl;

    public RequestTransferCommand(String transferId, String balanceUrl, TransferModel model) {
        if (isNull(transferId) || "".equals(transferId)) {
            throw new IllegalArgumentException("transfer ID cannot be empty");
        }

        this.transferId = transferId;
        this.balanceUrl = balanceUrl;
        this.sourceAccount = model.getSourceAccount();
        this.targetAccount = model.getTargetAccount();
        this.amount = model.getAmount();
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String value) { this.transferId = value; }

    public String getSourceAccount() { return sourceAccount; }

    public String getTargetAccount() { return targetAccount; }

    public Double getAmount() { return amount; }

    public String getBalanceUrl() { return balanceUrl; }

}
