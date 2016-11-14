package bank;

import java.util.Calendar;

public class CreditAccount extends Account{
    private int interestRate; // Процентная ставка
    private int limitOfCard;  // Лимит по кредитной карте
    private int interestAccrued; // Начисленные проценты
    private int interestCommission; // Начисленные комиссионные

    public CreditAccount(int number, int balance, int commission, Currency currency, int interestRate) {
        super(number, balance, commission, currency);
        this.interestRate = interestRate;
        interestCommission = 0;
    }

    public CreditAccount(int number, int balance, int commission) {
        super(number, balance, commission);
    }

    public CreditAccount(int number, int balance, int commission, Currency currency) {
        super(number, balance, commission, currency);
    }

    CreditAccount(int number, int balance) {
        super(number, balance);
        setCommission(0);
    }

    CreditAccount(int number) {
        super(number);
        super.addedSum(0);
        setCommission(0);
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getInterestCommission() {
        return interestCommission;
    }

    public int getInterestAccrued() {
        return interestAccrued;
    }

    // Начисление процентов
    public void interestAccrued() {

        // Вычисляем кол-во дней в текущем году...
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int days;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            days = 366;
        } else { days = 365; }

        if (this.getbalance() < limitOfCard) {
            interestAccrued += (limitOfCard - getbalance()) * (interestRate / days) / 100;
        }
    }

    // Начисление комиссионных
    @Override
    public void addedCommissions() {
        interestCommission += getCommission();
    }

    // Пополнения счета
    @Override
    public void addedSum(int balance) {
        if (interestCommission > 0) {
            if (interestCommission > balance) {
                interestCommission -= balance;
                balance = 0;
            } else {
                balance -= interestCommission;
                interestCommission = 0;
            }
        }

        if (interestAccrued > 0 && balance > 0) {
            if (interestAccrued > balance) {
                interestAccrued -= balance;
                balance = 0;
            } else {
                balance -= interestAccrued;
                interestAccrued = 0;
            }
        }

        super.addedSum(balance);

    }

    @Override
    public void substractionSum(int balance) throws InsufficientFundsException {
        this.addedSum(balance * (-1));
    }
}
