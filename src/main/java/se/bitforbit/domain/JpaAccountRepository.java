package se.bitforbit.domain;

import se.bitforbit.contexts.transfermoney.TransferMoneyRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JpaAccountRepository  implements TransferMoneyRepository {

    private Map<String, Account> repo = new HashMap<String, Account>();

    @Override
    public Account save(Account account) {
        if(account.id() == null){
            account.id(UUID.randomUUID().toString());
        }
        repo.put(account.id(), account);
        return account;
    }

    public Account FindAccountById(String id){
        return repo.get(id);
    }
}

