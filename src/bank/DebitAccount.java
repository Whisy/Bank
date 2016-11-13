package bank;

public class DebitAccount extends Account{

    public DebitAccount(int number, int balance, int commission){
        super(number, balance, commission);

    }

    public DebitAccount(int number, int balance, int commission, Currency currency) {
        super(number, balance, commission, currency);

    }

    public DebitAccount(int number, int balance) {
        super(number, balance);

    }

    public DebitAccount(int number) {
        super(number);

    }
}
