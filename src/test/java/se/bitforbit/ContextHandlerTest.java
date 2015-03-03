package se.bitforbit;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;
import se.bitforbit.domain.Account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ContextHandlerTest {



    @Test
    public void sdfsd(){
        Account account1 = setUpNewAccount("123456", 100);
        Account account2 = setUpNewAccount("987654", 100);

        TransferMoney_123 context = ContextHandlerBuilder
                .context_TransferMoney_123()
                .withSource(account1)
                .withDestination(account2)
                .withAmount(95)
                .build();

        TransferMoneyResult result = context.transferMoney();
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getDestinationAmount(), is(195));
        assertThat(result.getSourceAmount(), is(5));

    }

    @Test
    public void asdads(){
        EventBus bus = new EventBus();
        bus.register(this);
        bus.post("Hello");
    }

    @Subscribe
    public void listen(String e){
        System.out.println(e);
    }

    private Account setUpNewAccount(String accountNumber, Integer amount) {
        Account result = new Account(accountNumber);
        result.addAmount(amount);
        return result;
    }
}
