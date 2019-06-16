package br.com.accenture.wallet.transaction.domain;

import br.com.accenture.wallet.transaction.command.RequestTransferCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Transfer {

    @AggregateIdentifier
    private String transferId;

    @CommandHandler
    public void p2pTransfer(final RequestTransferCommand cmd) {

    }

}
