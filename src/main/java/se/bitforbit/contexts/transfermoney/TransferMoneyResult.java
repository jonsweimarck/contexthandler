package se.bitforbit.contexts.transfermoney;


import se.bitforbit.domain.Account;

public class TransferMoneyResult {

    public enum TransferMoneyFailReason {
        SOURCE_INSUFFICIENT_AMOUNT
    }

    boolean success;
    TransferMoneyFailReason failReason;

    private Account source;
    private Account destination;
    private Integer transferredAmount;

    private TransferMoneyResult(boolean succes, Account sourceAmount, Account destinationAmount, Integer transferredAmount){
        this.success = succes;
        this.source = sourceAmount;
        this.destination = destinationAmount;
        this.transferredAmount = transferredAmount;
    }

    public static TransferMoneyResult SUCCESS(Account sourceAmount, Account destinationAmount, Integer transferredAmount) {
        TransferMoneyResult result = new TransferMoneyResult(true, sourceAmount, destinationAmount, transferredAmount);
        return result;
    }

    public static TransferMoneyResult FAIL(TransferMoneyFailReason failReason, Account sourceAmount, Account destinationAmount, Integer transferredAmount) {
        TransferMoneyResult result = new TransferMoneyResult(false, sourceAmount, destinationAmount, transferredAmount);
        result.failReason = failReason;
        return result;
    }

    public Integer getTransferredAmount() {
        return transferredAmount;
    }

    public TransferMoneyFailReason getFailReason() {
        return failReason;
    }

    public Account getSource() {
        return source;
    }

    public Account getDestination() {
        return destination;
    }

    public boolean isSuccess(){
        return success;
    }
    public boolean isFail(){
        return ! isSuccess();
    }

}
