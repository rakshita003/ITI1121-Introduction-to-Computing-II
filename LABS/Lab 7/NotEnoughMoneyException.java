public class NotEnoughMoneyException extends IllegalStateException{
    private double amount,balance;
    public NotEnoughMoneyException(double amount,double balance){
        this.amount = amount;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }
    public double getMissingAmount(){
        return amount - balance;
    }
}
