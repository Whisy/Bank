package bank;


import java.util.ArrayList;
import java.util.Arrays;

public class NaturalClient implements Client{
    String name;
    String surname;
    int series;
    int numberOfThePassport;
    ArrayList<Account> accountList;

    public Account[] accounts;

    NaturalClient(String name, String surname, int series, int numberOfThePassport) {
        this.name = name;
        this.surname = surname;
        this.series = series;
        this.numberOfThePassport = numberOfThePassport;
        accounts = new Account[0];
    }

    NaturalClient(String name, String surname, int series, int numberOfThePassport, Account[] Accounts) {
        this.name = name;
        this.surname = surname;
        this.series = series;
        this.numberOfThePassport = numberOfThePassport;
        this.accounts = Accounts;
    }


    public void numberException(int number) {
        for (Account acc : accounts) {
            if (acc.number == number) throw new DuplicateAccountNumber();
        }
    }

    public int score(int number) {
        int i = 0;
        for (i = 0; i < accounts.length; i++)
            if (number == (accounts[i].getnumber()))
                break;
        return accounts[i].getbalance();

    }
    public Account[] getAccounts(){
        return accounts;
    }

    public int rest(){
        int i = 0;
        int sum = 0;
        for(i = 0; i< accounts.length; i++)
            sum +=accounts[i].getbalance();
        return sum;
    }

    public Account[] positiveBalance(){
        accountList = new ArrayList<Account>();
        for (Account acc : accounts) {
            if (acc.getbalance() > 0) accountList.add(acc);
        }
        if (accountList.size() == 0) return null;
        else return accountList.toArray(new Account[accountList.size()-1]);

    }

    public void delete(int number) {
        int i;
        Account[] newAccounts = new Account[accounts.length-1];
        for(i=0; i<accounts.length; i++) {
            if (number==accounts[i].getnumber())
                break;

        }
        if (i==accounts.length)
            System.out.println("The account number isn't found. Recheck data. ");
        else {
            System.arraycopy(accounts, 0, newAccounts, 0, i);
            System.arraycopy(accounts, i + 1, newAccounts, i, newAccounts.length - i);
        }
        accounts=newAccounts;

    }
    public void off() {
        int h = 0;
        for(h=0; h < accounts.length; h++)
            System.out.println(accounts[h].getnumber() + " , " + accounts[h].getbalance() );
    }

    public void newScore(int number, int balance) {
        numberException(number);
        Account[] newAccounts = new Account[accounts.length+1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[ newAccounts.length-1]=new DebitAccount(number, balance);
        accounts = newAccounts;
    }

    public void reductionOfTheScore(int number, int reductionOfTheScore) throws InsufficientFundsException {
        int i = 0 ;
        for (i = 0; i < accounts.length; i++)
            if (number == (accounts[i].getnumber()))
                break;

        if(i==accounts.length)
            System.out.println("The account number isn't found. Recheck data. ");
        else accounts[i].substractionSum(reductionOfTheScore);

    }

    public void increaseOfTheScore(int number, int increaseOfTheScore){
        int i = 0;
        for (i = 0; i < accounts.length; i++)
            if (number == (accounts[i].getnumber()))
                break;

        if(i==accounts.length)
            System.out.println("The account number isn't found. Recheck data. ");
        else accounts[i].addedSum(increaseOfTheScore);

    }


    @Override
    public Account getReference(int number) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getnumber() == number) return accounts[i];
        }
        return null;
    }

    @Override
    public ArrayList<Account> geAllAccountList() {
        return new ArrayList<Account>(Arrays.asList(accounts));
    }

    @Override
    public ArrayList<Account> getDebitAccountList() {
        accountList.clear();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getClass() == DebitAccount.class) {
                accountList.add(accounts[i]);
            }
        }
        return accountList;
    }

    @Override
    public ArrayList<Account> getCreditAccountList() {
        accountList.clear();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getClass() == CreditAccount.class) {
                accountList.add(accounts[i]);
            }
        }
        return accountList;
    }

    @Override
    public int getTotalBalance() {
        return rest();
    }

    @Override
    public long getTotalCreditBalance() {
        long total = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getClass() == CreditAccount.class) {
                total += accounts[i].getbalance();
            }
        }
        return total;
    }

    @Override
    public ArrayList<Account> getDebitCreditCardsOfPlus() {
        accountList = new ArrayList<Account>();
        for (Account acc : accounts) {
            if (acc.getbalance() > 0) {
                accountList.add(acc);
            }
        }
        return accountList;
    }

    @Override
    public void deleteAccount(int number) {
        delete(number);
    }

    @Override
    public void AddAccount(Account account) {
        Account[] tempAccounts = new Account[accounts.length+1];
        tempAccounts = accounts.clone();
        tempAccounts[tempAccounts.length-1] = account;
        accounts = new Account[tempAccounts.length];
        accounts = tempAccounts.clone();

    }

    @Override
    public void substractSum(int number, int balance) throws InsufficientFundsException {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getnumber() == number) accounts[i].substractionSum(balance);
        }
    }

    @Override
    public void AddedSum(int number, int balance) {
        increaseOfTheScore(number, balance);

    }
}

    class Demo {
        public static void main(String[] args) {
            NaturalClient cli = new NaturalClient("Petr", "Ivanov", 3610, 233729);
            cli.accounts = new Account[3];
            cli.accounts[0] = new DebitAccount(28898765, 701);
            cli.accounts[1] = new DebitAccount(23334567, 5789);
            cli.accounts[2] = new DebitAccount(44446789, 710);
            cli.off();
            Account[] acc = cli.positiveBalance();
            for (int i = 0; i < acc.length; i++) {
                System.out.println("Positive balance: " + acc[i].getbalance());
            }
            cli.off();


        }
    }
