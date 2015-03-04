package se.bitforbit.contexts.transfermoney;

import se.bitforbit.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeTransferMoneyRepo implements TransferMoneyRepository {
    private Map<String, Account> repo = new HashMap<String, Account>();

    @Override
    public Account save(Account account) {
        if(account.id() == null){
            account.id(UUID.randomUUID().toString());
        }
        repo.put(account.id(), account);
        return account;
    }

    public Account FindAccountByAccountNumber(String accountNumber){
       for(Account account: repo.values()){
           if(account.accountNbr().equals(accountNumber)){
               return account;
           }
       }
       return null;
    }
}
