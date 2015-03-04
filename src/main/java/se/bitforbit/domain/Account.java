package se.bitforbit.domain;

public class Account {
    String id;
    String accountNbr;
    Integer amount = 0;

    public Account(String accountNbr) {
        this.accountNbr = accountNbr;
    }

    public String id() {
        return id;
    }

    public void id(String id){
        this.id = id;
    }

    public String accountNbr() {
        return accountNbr;
    }

    public void addAmount(Integer amount){
        this.amount += amount;
    }

    public void subAmount(Integer amount){
        this.amount -= amount;
    }

    public Integer amount() {
        return amount;
    }

}
