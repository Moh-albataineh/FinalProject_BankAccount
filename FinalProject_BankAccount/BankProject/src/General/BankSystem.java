/**
 * @author Mohammad Albatainah
 * @version 2025-12-7
 */

package General;

import java.util.ArrayList;

/**
 * BankSystem class manages multiple bank accounts in the application.
 * Serves as the main controller that performs operations such as creating accounts,
 * searching for accounts, depositing/withdrawing money, transferring funds,
 * and editing account information.
 */
public class BankSystem {
    // Instance variable
    private ArrayList<BankAccount> accounts;
    
    /**
     * Constructor
     * Initializes the accounts list as an empty ArrayList
     */
    public BankSystem() {
        this.accounts = new ArrayList<>();
    }
    
    /**
     * Creates a new bank account and adds it to the system
     * @param ownerName The name of the account owner
     * @param initialBalance The starting balance
     * @return The newly created BankAccount object
     * @throws IllegalArgumentException if initial balance is negative
     */
    public BankAccount createAccount(String ownerName, double initialBalance) {
        BankAccount newAccount = new BankAccount(ownerName, initialBalance);
        accounts.add(newAccount);
        return newAccount;
    }
    
    /**
     * Finds an account by its account number
     * @param accountNumber The account number to search for
     * @return The BankAccount object if found
     * @throws IllegalArgumentException if account is not found
     */
    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found: " + accountNumber);
    }
    
    /**
     * Deposits money into the specified account
     * @param accountNumber The account to deposit into
     * @param amount The amount to deposit
     * @throws IllegalArgumentException if account not found or amount is invalid
     */
    public void deposit(int accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.deposit(amount);
    }
    
    /**
     * Withdraws money from the specified account
     * @param accountNumber The account to withdraw from
     * @param amount The amount to withdraw
     * @throws IllegalArgumentException if account not found, amount invalid, or insufficient funds
     */
    public void withdraw(int accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.withdraw(amount);
    }
    
    /**
     * Transfers money from one account to another
     * @param fromAccount The source account number
     * @param toAccount The destination account number
     * @param amount The amount to transfer
     * @throws IllegalArgumentException if either account not found, amount invalid, or insufficient funds
     */
    public void transfer(int fromAccount, int toAccount, double amount) {
        BankAccount sourceAccount = findAccount(fromAccount);
        BankAccount destinationAccount = findAccount(toAccount);
        
        // Withdraw from source, then deposit to destination
        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);
    }
    
    /**
     * Removes an account from the system
     * @param accountNumber The account number to remove
     * @return true if account was found and removed, false otherwise
     */
    public boolean removeAccount(int accountNumber) {
        try {
            BankAccount account = findAccount(accountNumber);
            return accounts.remove(account);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Changes the owner name of an account
     * @param accountNumber The account to modify
     * @param newName The new owner name
     * @throws IllegalArgumentException if account not found or name is null/empty
     */
    public void changeOwnerName(int accountNumber, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        BankAccount account = findAccount(accountNumber);
        account.setOwnerName(newName);
    }
    
    /**
     * Retrieves the balance of a specific account
     * @param accountNumber The account to query
     * @return The current balance
     * @throws IllegalArgumentException if account not found
     */
    public double getAccountBalance(int accountNumber) {
        BankAccount account = findAccount(accountNumber);
        return account.getBalance();
    }
    
    /**
     * Retrieves the full transaction statement for an account
     * @param accountNumber The account to query
     * @return A formatted string of all transactions
     * @throws IllegalArgumentException if account not found
     */
    public String getAccountStatement(int accountNumber) {
        BankAccount account = findAccount(accountNumber);
        return account.getStatement();
    }
    
    /**
     * Returns all accounts in the system
     * @return ArrayList of all BankAccount objects
     */
    public ArrayList<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts); 
    }
    
    /**
     * Returns the total number of accounts in the system
     * @return The count of active accounts
     */
    public int getAccountCount() {
        return accounts.size();
    }
    
    /**
     * Returns a summary of all accounts in the system
     * @return A formatted string listing all accounts
     */
    public String getSystemSummary() {
        if (accounts.isEmpty()) {
            return "No accounts in the system.";
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("=== Bank System Summary ===\n");
        summary.append(String.format("Total Accounts: %d\n\n", accounts.size()));
        
        for (BankAccount account : accounts) {
            summary.append(account.toString()).append("\n");
        }
        
        return summary.toString();
    }

}
