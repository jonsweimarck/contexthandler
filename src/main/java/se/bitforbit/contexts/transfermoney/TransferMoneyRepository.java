package se.bitforbit.contexts.transfermoney;

import se.bitforbit.domain.Account;

public interface TransferMoneyRepository {

    Account save(Account source);
}
