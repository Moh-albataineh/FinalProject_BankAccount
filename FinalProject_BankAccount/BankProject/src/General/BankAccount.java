/**
 * @author Mohammad Albatainah
 * @version 2025-12-7
 */

package General;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * BankAccount class models a simple bank account with deposit, withdrawal,
 * transaction tracking, and statement generation capabilities.
 */
public class BankAccount {
    // Instance variables
    private int accountNumber;
    private String ownerName;
    private double balance;
    private StringBuilder transactions;
    
    // Static counter for unique account numbers
    private static int nextAccountNumber = 1000;
    
    /**
     * Default constructor
     * Initializes account with empty owner name and zero balance
     */
    public BankAccount() {
        this.ownerName = "";
        this.balance = 0.0;
        this.accountNumber = nextAccountNumber++;
        this.transactions = new StringBuilder();
    }
    
    /**
     * Overloaded constructor
     * @param ownerName The name of the account owner
     * @param initialBalance The starting balance
     * @throws IllegalArgumentException if initialBalance is negative
     */
    public BankAccount(String ownerName, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.accountNumber = nextAccountNumber++;
        this.transactions = new StringBuilder();
        
        // Record initial balance if greater than zero
        if (initialBalance > 0) {
            addTransactionRecord("Initial Deposit", initialBalance);
        }
    }
    
    /**
     * Deposits money into the account
     * @param amount The amount to deposit
     * @throws IllegalArgumentException if amount is not positive
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        addTransactionRecord("Deposit", amount);
    }
    
    /**
     * Withdraws money from the account
     * @param amount The amount to withdraw
     * @throws IllegalArgumentException if amount is not positive or exceeds balance
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
        addTransactionRecord("Withdraw", amount);
    }
    
    /**
     * Returns the current account balance
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Returns the unique account number
     * @return The account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * Returns the owner's name
     * @return The owner name
     */
    public String getOwnerName() {
        return ownerName;
    }
    
    /**
     * Sets the owner's name
     * @param name The new owner name
     */
    public void setOwnerName(String name) {
        this.ownerName = name;
    }
    
    /**
     * Returns the complete transaction statement
     * @return A formatted string of all transactions
     */
    public String getStatement() {
        if (transactions.length() == 0) {
            return "No transactions recorded.";
        }
        return transactions.toString();
    }
    
    /**
     * Private helper method to record a transaction with date/time
     * @param type The transaction type (e.g., "Deposit", "Withdraw")
     * @param amount The transaction amount
     */
    private void addTransactionRecord(String type, double amount) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateTime = sdf.format(cal.getTime());
        
        transactions.append(String.format("[%s] %s: $%.2f | New Balance: $%.2f%n", 
                                         dateTime, type, amount, balance));
    }
    
    /**
     * Returns a string representation of the account
     * @return Account summary
     */
    @Override
    public String toString() {
        return String.format("Account #%d - Owner: %s - Balance: $%.2f", 
                           accountNumber, ownerName, balance);
    }
}