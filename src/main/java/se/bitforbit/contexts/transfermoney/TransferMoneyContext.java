package se.bitforbit.contexts.transfermoney;

import se.bitforbit.domain.Account;


public class TransferMoneyContext {

    private Account source;
    private Account destination;
    private Integer amount;
    private TransferMoneyRepository transferMoneyRepository;

    protected TransferMoneyContext(Account source, Account destination, Integer amount, TransferMoneyRepository transferMoneyRepository) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.transferMoneyRepository = transferMoneyRepository;
    }

    public TransferMoneyResult transferMoney() {
        if(source.amount() < amount){
            return TransferMoneyResult.FAIL(TransferMoneyResult.TransferMoneyFailReason.SOURCE_INSUFFICIENT_AMOUNT, source, destination, amount);
        }

        source.subAmount(amount);
        destination.addAmount(amount);

        source = transferMoneyRepository.save(source);
        destination = transferMoneyRepository.save(destination);

        return  TransferMoneyResult.SUCCESS(source, destination, amount);
    }
}
