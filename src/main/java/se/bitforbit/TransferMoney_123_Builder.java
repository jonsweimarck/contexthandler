package se.bitforbit;

import se.bitforbit.domain.Account;

public class TransferMoney_123_Builder {

    private Account source;
    private Account destination;
    private Integer amount;

    public TransferMoney_123_Builder withSource(Account source){
        this.source = source;
        return this;
    }

    public TransferMoney_123_Builder withDestination(Account destination){
        this.destination = destination;
        return this;
    }

    public TransferMoney_123_Builder withAmount(Integer amount){
        this.amount = amount;
        return this;
    }

    public TransferMoney_123 build(){
        return new TransferMoney_123(source, destination, amount);
    }
}
