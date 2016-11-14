package bank;

public abstract class Account
{
    private int number;
    private int balance;
    private int commission;
    private Currency currency;

    public Account(int number, int balance, int commission) {
        currency = Currency.RUB;
        this.number = number;
        this.balance = balance;
        this.commission = commission;
        if (number < 0 || balance < 0 || commission < 0) {
            throw new IllegalArgumentException();
        }

    }

    public Account(int number, int balance, int commission, Currency currency) {
        this.number = number;
        this.balance = balance;
        this.commission = commission;
        this.currency = currency;
        if (number < 0 || balance < 0 || commission < 0) {
            throw new IllegalArgumentException();
        }
    }

    Account(int number, int balance)
    {
        this.number = number;
        this.balance = balance;
        if (number < 0 || balance < 0) {
            throw new IllegalArgumentException();
        }
    }

    Account(int number)
    {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.number = number;
        balance = 0;

    }

    public int getnumber()
    {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }

    // Получить остаток средств на счете
    public int getbalance() {
        return balance;
    }

    // Получить размер комиссии
    public int getCommission() {
        return commission;
    }

    // Установка размера комиссии
    public void setCommission(int commission) {
        this.commission = commission;
    }

    // Получение текущей валюты
    public Currency getCurrency() {
        return currency;
    }

    // Установка валюты
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    // Списание комиссии
    public void addedCommissions() throws InsufficientFundsException {
        try {
            if (commission > this.balance) throw new InsufficientFundsException("Not enough money");
            this.balance -= commission;
        } catch (InsufficientFundsException e)
        {
            System.out.println(e);
        }
    }

    // Списание средств со счета
    public void substractionSum(int balance) throws InsufficientFundsException {
        try {
            if (balance > this.balance) throw new InsufficientFundsException("Not enough money");
            this.balance -= balance;
        } catch (InsufficientFundsException e)
        {
            System.out.println(e);
        }

    }

    // Пополнения счета
    public void addedSum(int balance) {
        this.balance += balance;
    }

}
