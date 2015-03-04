package se.bitforbit.contexts.transfermoney;

import org.junit.Test;
import se.bitforbit.ContextBuilder;
import se.bitforbit.domain.Account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static se.bitforbit.contexts.transfermoney.TransferMoneyResult.TransferMoneyFailReason.SOURCE_INSUFFICIENT_AMOUNT;

public class TransferMoneyContextTest {


    @Test
    public void contextShouldWorkWhenHappyPath(){

        Account sourceAccount = setUpNewAccount("123456", 100);
        Account destinationAccount = setUpNewAccount("987654", 100);

        FakeTransferMoneyRepo fakeTransferMoneyRepo = new  FakeTransferMoneyRepo();
        fakeTransferMoneyRepo.save(sourceAccount);
        fakeTransferMoneyRepo.save(destinationAccount);

        ContextBuilder contextHandlerBuilderWithFakeRepo = createContextHandlerBuilderWithFakeRepo(fakeTransferMoneyRepo);

        TransferMoneyContext context = contextHandlerBuilderWithFakeRepo
                .createContext_TransferMoney()
                .withSource(sourceAccount)
                .withDestination(destinationAccount)
                .withAmount(95)
                .build();

        TransferMoneyResult result = context.transferMoney();
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getDestination().amount(), is(195));
        assertThat(result.getSource().amount(), is(5));

        // Accounts also updated in db
        assertThat(fakeTransferMoneyRepo.FindAccountByAccountNumber("123456").amount(), is(5));
        assertThat(fakeTransferMoneyRepo.FindAccountByAccountNumber("987654").amount(), is(195));
    }

    @Test
    public void contextFailsIfSourceAccountLacksMoney(){

        Account sourceAccount = setUpNewAccount("123456", 50);
        Account destinationAccount = setUpNewAccount("987654", 100);

        FakeTransferMoneyRepo fakeTransferMoneyRepo = new  FakeTransferMoneyRepo();
        fakeTransferMoneyRepo.save(sourceAccount);
        fakeTransferMoneyRepo.save(destinationAccount);

        ContextBuilder contextHandlerBuilderWithFakeRepo = createContextHandlerBuilderWithFakeRepo(fakeTransferMoneyRepo);

        TransferMoneyContext context = contextHandlerBuilderWithFakeRepo
                .createContext_TransferMoney()
                .withSource(sourceAccount)
                .withDestination(destinationAccount)
                .withAmount(95)
                .build();

        TransferMoneyResult result = context.transferMoney();
        assertThat(result.isFail(), is(true));
        assertThat(result.failReason, is(SOURCE_INSUFFICIENT_AMOUNT));
        assertThat(result.getDestination().amount(), is(100));
        assertThat(result.getSource().amount(), is(50));

        // Accounts not updated in db
        assertThat(fakeTransferMoneyRepo.FindAccountByAccountNumber("123456").amount(), is(50));
        assertThat(fakeTransferMoneyRepo.FindAccountByAccountNumber("987654").amount(), is(100));
    }

    private ContextBuilder createContextHandlerBuilderWithFakeRepo(FakeTransferMoneyRepo fakeTransferMoneyRepo) {
        ContextBuilder result = new ContextBuilder();
        result.setTransferMoneyRepository(fakeTransferMoneyRepo);
        return result;
    }


    private Account setUpNewAccount(String accountNumber, Integer amount) {
        Account result = new Account(accountNumber);
        result.addAmount(amount);
        return result;
    }
}
