package bank;

import java.util.ArrayList;
import java.util.List;

public interface Client {
    Account getReference(int number);

    List<Account> geAllAccountList();

    ArrayList<Account> getDebitAccountList();

    ArrayList<Account> getCreditAccountList();

    // Получить сумму на всех счетах клиента
    int getTotalBalance();

    // Получить задолженность по всем кредитным картам клиента
    long getTotalCreditBalance();

    // Получить список счетов с положительным балансом
    ArrayList<Account> getDebitCreditCardsOfPlus();

    // Удалить счет по номеру
    void deleteAccount(int number);

    // Добавление счета
    void AddAccount(Account account);

    // Списывание средств со счета
    void substractSum(int number, int balance) throws InsufficientFundsException;

    // Пополнение счета
    void AddedSum(int number, int balance);

}
