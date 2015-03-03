package se.bitforbit;

import com.google.common.eventbus.EventBus;
import se.bitforbit.domain.Account;
import static se.bitforbit.TransferMoneyResult.TransferMoneyFailReason.*;


public class TransferMoney_123 {

    private Account source;
    private Account destination;
    private Integer amount;
    private EventBus eventBus;

    protected TransferMoney_123(Account source, Account destination, Integer amount, EventBus eventBus) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.eventBus = eventBus;
    }

    public TransferMoneyResult transferMoney() {
        if(source.amount() < amount){
            return TransferMoneyResult.FAIL(SOURCE_INSUFFICIENT_AMOUNT, source, destination, amount);
        }

        source.subAmount(amount);
        destination.addAmount(amount);

        eventBus.post(new MoneyTransferredEvent())

        return  TransferMoneyResult.SUCCESS(source, destination, amount);
    }
}
