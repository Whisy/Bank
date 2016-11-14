package bank;


import java.util.ArrayList;
import java.util.List;

public class NaturalClient implements Client{
    private String name;
    private String surname;
    private int series;
    private int numberOfThePassport;
    private List<Account> accountList = new ArrayList<Account>();

    NaturalClient(String name, String surname, int series, int numberOfThePassport) {
        this.name = name;
        this.surname = surname;
        this.series = series;
        this.numberOfThePassport = numberOfThePassport;
    }

    NaturalClient(String name, String surname, int series, int numberOfThePassport, List<Account> acc) {
        this.name = name;
        this.surname = surname;
        this.series = series;
        this.numberOfThePassport = numberOfThePassport;
        accountList = acc;
    }


    public void numberException(int number) {
        for (Account acc : accountList) {
            if (acc.getnumber() == number) throw new DuplicateAccountNumber();
        }
    }

    public int score(int number) {
        for (Account acc: accountList) {
            if (acc.getnumber() == number) {
                return acc.getbalance();
            }
        }
        return 0;
    }
    public List<Account> getAccounts(){
        return accountList;
    }

    public int rest(){
        int sum = 0;
        for (Account acc : accountList) {
            sum += acc.getbalance();
        }
        return sum;
    }

    public List<Account> positiveBalance(){
        List<Account> accountList2 = new ArrayList<Account>();
        for (Account acc : accountList) {
            if (acc.getbalance() > 0) accountList2.add(acc);
        }
        if (accountList2.size() == 0) return null;
        else return accountList2;

    }

    public void delete(int number) {

        List<Account> accountList2 = new ArrayList<Account>();
        for (Account acc : accountList) {
            if (acc.getnumber() != number)
                accountList2.add(acc);
        }
        if (accountList2.size() == accountList.size())
            System.out.println("The account number isn't found. Recheck data. ");
        else {
            accountList.clear();
            accountList = accountList2;
        }
    }

    public void off() {
        int h = 0;
        for(Account acc : accountList)
            System.out.println(acc.getnumber() + " , " + acc.getbalance() );
    }

    public void newDebitScore(int number, int balance) {
        numberException(number);
        accountList.add(new DebitAccount(number, balance));
    }

    public void newCreditScore(int number, int balance) {
        numberException(number);
        accountList.add(new CreditAccount(number, balance));
    }

    public void reductionOfTheScore(int number, int reductionOfTheScore) throws InsufficientFundsException {
        boolean substraction = false;
        for (Account acc : accountList) {
            if (acc.getnumber() == number) {
                acc.substractionSum(reductionOfTheScore);
                substraction = true;
            }
            break;
        }

        if(!substraction)
            System.out.println("The account number isn't found. Recheck data. ");

    }

    public void increaseOfTheScore(int number, int increaseOfTheScore){
        boolean substraction = false;
        for (Account acc : accountList) {
            if (acc.getnumber() == number) {
                acc.addedSum(increaseOfTheScore);
                substraction = true;
            }
            break;
        }

        if(!substraction)
            System.out.println("The account number isn't found. Recheck data. ");

    }


    @Override
    public Account getReference(int number) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getnumber() == number) return accountList.get(i);
        }
        return null;
    }

    @Override
    public ArrayList<Account> geAllAccountList() {
        return (ArrayList<Account>)accountList;
    }

    @Override
    public ArrayList<Account> getDebitAccountList() {
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account acc : accountList) {
            if (acc instanceof DebitAccount) accounts.add(acc);
        }
        return accounts;
    }

    @Override
    public ArrayList<Account> getCreditAccountList() {
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account acc : accountList) {
            if (acc instanceof CreditAccount) accounts.add(acc);
        }
        return accounts;
    }

    @Override
    public int getTotalBalance() {
        return rest();
    }

    @Override
    public long getTotalCreditBalance() {
        long total = 0;
        for (Account acc : accountList) {
            if (acc instanceof CreditAccount) total += acc.getbalance();
        }
        return total;
    }

    @Override
    public ArrayList<Account> getDebitCreditCardsOfPlus() {
        ArrayList<Account> accountList2 = new ArrayList<Account>();
        for (Account acc : accountList) {
            if (acc.getbalance() > 0) {
                accountList2.add(acc);
            }
        }
        return accountList2;
    }

    @Override
    public void deleteAccount(int number) {
        delete(number);
    }

    @Override
    public void AddAccount(Account account) {
        accountList.add(account);
    }

    @Override
    public void substractSum(int number, int balance) throws InsufficientFundsException {
        for (Account acc : accountList) {
            if (acc.getnumber() == number) acc.substractionSum(balance);
        }
    }

    @Override
    public void AddedSum(int number, int balance) {
        increaseOfTheScore(number, balance);

    }
}

    class Demo {
        public static void main(String[] args) throws InsufficientFundsException {
            NaturalClient cli = new NaturalClient("Petr", "Ivanov", 3610, 233729);
            cli.newDebitScore(28898765, 701);
            cli.newCreditScore(23334567, 5789);
            cli.newDebitScore(44446789, 710);
            cli.off();
            cli.substractSum(23334567, 6000);
            cli.substractSum(44446789, 6000);
            cli.off();


        }
    }
