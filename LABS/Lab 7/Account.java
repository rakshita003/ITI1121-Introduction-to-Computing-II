public class Account {
    public double balance = 0;

    public double deposit(double dvalue){
    balance = balance + dvalue;
    return balance;
    }
    public double withdraw(double wvalue) {
        if (wvalue > balance) {
            throw new NotEnoughMoneyException(wvalue,balance);
        } else {
            balance = balance - wvalue;
            return balance;
        }
    }

    public double getBalance() {
        return balance;
    }

}
