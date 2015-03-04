package se.bitforbit.contexts.transfermoney;

import se.bitforbit.domain.Account;

public class TransferMoneyContextBuilder {

    private TransferMoneyRepository transferMoneyRepository;

    private Account source;
    private Account destination;
    private Integer amount;

    public TransferMoneyContextBuilder(TransferMoneyRepository transferMoneyRepository){
        this.transferMoneyRepository = transferMoneyRepository;
    }

    public TransferMoneyContextBuilder withSource(Account source){
        this.source = source;
        return this;
    }

    public TransferMoneyContextBuilder withDestination(Account destination){
        this.destination = destination;
        return this;
    }

    public TransferMoneyContextBuilder withAmount(Integer amount){
        this.amount = amount;
        return this;
    }

    public TransferMoneyContext build(){
        return new TransferMoneyContext(source, destination, amount, transferMoneyRepository);
    }
}
