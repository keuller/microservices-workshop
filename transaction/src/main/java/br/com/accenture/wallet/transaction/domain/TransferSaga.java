package br.com.accenture.wallet.transaction.domain;

import br.com.accenture.wallet.transaction.domain.command.*;
import static br.com.accenture.wallet.transaction.domain.OperationType.TRANSFER;
import br.com.accenture.wallet.transaction.domain.event.*;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class TransferSaga {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private transient CommandGateway commandGateway;
    private String transferId;
    private String targetAccountId;

    private CommandCallback<WithdrawalCommand, Object> withdrawnHandler = (message, result) -> {
        if (result.isExceptional()) {
            log.error(result.exceptionResult().getMessage()); // , result.exceptionResult()
            commandGateway.send(new TransferCancelCommand(transferId).withCause(result.exceptionResult()));
        }
    };

    @Autowired
    public void setCommandGateway(CommandGateway cmdGateway) {
        this.commandGateway = cmdGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void onTransferRequested(TransferRequestedEvent event) {
        this.transferId = event.getTransferId();
        this.targetAccountId = event.getTargetAccount();

        log.info("Starting transfer saga {}", transferId);
        SagaLifecycle.associateWith("transactionId", transferId);

        final WithdrawalCommand cmd = new WithdrawalCommand(TRANSFER)
            .withAccount(event.getSourceAccount())
            .withTransactionId(transferId)
            .withValue(event.getAmount());

        commandGateway.send(cmd, withdrawnHandler);
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void onWithdrawn(WithdrawnEvent event) {
        final DepositCommand cmd = new DepositCommand(TRANSFER)
            .withAccount(targetAccountId)
            .withTransactionId(event.getTransactionId())
            .withValue(event.getValue());

        commandGateway.send(cmd);
    }

    @SagaEventHandler(associationProperty = "transferId")
    public void onDeposited(DepositedEvent event) {
        commandGateway.send(new TransferCompleteCommand(transferId));
    }

    @SagaEventHandler(associationProperty = "transferId")
    public void onCacelTransfer(TransferCancelledEvent event) {
        log.debug("Cancelling transfer flow for '{}'", event.getCause());
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void onTransferCompleted(TransferCompletedEvent event) {
        log.info("Transfer saga {} has been completed successfuly.", transferId);
    }

}
