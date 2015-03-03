package se.bitforbit;

import se.bitforbit.domain.Account;

public class TransferMoneyResult {

    public enum TransferMoneyFailReason {
        SOURCE_INSUFFICIENT_AMOUNT
    }

    boolean success;
    TransferMoneyFailReason failReason;

    private Account source;
    private Account destination;
    private Integer amount;

    private TransferMoneyResult(boolean succes, Account source, Account destination, Integer amount){
        this.success = succes;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public static TransferMoneyResult SUCCESS(Account source, Account destination, Integer amount) {
        TransferMoneyResult result = new TransferMoneyResult(true, source, destination,amount);
        return result;
    }

    public static TransferMoneyResult FAIL(TransferMoneyFailReason failReason, Account source, Account destination, Integer amount) {
        TransferMoneyResult result = new TransferMoneyResult(false, source, destination,amount);
        result.failReason = failReason;
        return result;
    }

    public Integer getSourceAmount() {
        return source.amount();
    }

    public Integer getDestinationAmount() {
        return destination.amount();
    }

    public boolean isSuccess(){
        return success;
    }
    public boolean isFail(){
        return ! isSuccess();
    }

}
