package se.bitforbit;

import se.bitforbit.contexts.transfermoney.TransferMoneyContextBuilder;
import se.bitforbit.contexts.transfermoney.TransferMoneyRepository;

public class ContextBuilder {

    private TransferMoneyRepository transferMoneyRepository = new ContextHandler.JpaAccountRepository();

    public ContextBuilder(){}

    public TransferMoneyContextBuilder createContext_TransferMoney() {
        return new TransferMoneyContextBuilder(transferMoneyRepository);
    }

    // For tests
    public void setTransferMoneyRepository(TransferMoneyRepository transferMoneyRepository) {
        this.transferMoneyRepository = transferMoneyRepository;
    }
}
