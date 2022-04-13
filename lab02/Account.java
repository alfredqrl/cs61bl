/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    private int balance;
    private Account parentAccount;

    /** Initialize an account with the given balance. */
    public Account(int balance,Account parentAccount) {
        this.balance = balance;
        this.parentAccount = parentAccount;
    }
    
    public Account (int balance){
        this.balance = balance;
        this.parentAccount = null;
    }
    

    /** Returns the balance for the current account. */
    public int getBalance() {
        return balance;
    }

    /** Deposits amount into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract amount from the account if possible. If subtracting amount
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
            return false;
        } else if (balance < amount) {
            if (parentAccount != null){
                if(parentAccount.balance < (amount - balance)){
                    System.out.println("Insufficient funds");
                    return false;
                }
                else{
                    parentAccount.balance -= (amount - balance);
                    balance = 0;
                    return true;
                }
            }
            else{
                System.out.println("Insufficient funds");
                return false;
            }
        } else {
            balance -= amount;
        }
        return true;
    }

    /**
     * Merge account other into this account by removing all money from other
     * and depositing it into this account.
     */
    public void merge(Account other) {
        if (other.balance != 0){
            int newone = other.balance;
            deposit(newone);
            other.balance = 0;
        }
    }

}
    