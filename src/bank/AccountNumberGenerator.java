package bank;

public class AccountNumberGenerator {

    private static int accountNumber = 0;

    public static void getNext() {
        accountNumber ++;
    }

    public static int getCurrent() {
        return accountNumber;
    }

    public void reset() {
        accountNumber = 0;
    }
}
