/**
 * Controller for handling GUI events and connecting user actions to the BankSystem logic.
 * @author Mohammad Albatainah
 * @version 2025-12-07
 */


package General;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class BankGUIController {

    
    @FXML private TextField ownerNameField;
    @FXML private TextField initialBalanceField;

    
    @FXML private TextField accountNumberField;
    @FXML private TextField amountField;

    
    @FXML private TextField targetAccountField;

    
    @FXML private TextField newOwnerNameField;

    
    @FXML private TextArea outputArea;

    private final BankSystem bankSystem = new BankSystem();
    
    /**
     * Initializes GUI components.
     */

    @FXML
    private void initialize() {
        outputArea.appendText("Welcome to the Bank System!\n");
    }

    /**
     * Creates a new bank account.
     * @param event button click event
     */

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        try {
            String owner = ownerNameField.getText();
            double balance = Double.parseDouble(initialBalanceField.getText());

            BankAccount account = bankSystem.createAccount(owner, balance);
            int accountId = account.getAccountNumber();

            outputArea.appendText("Created account #" + accountId + " for " + owner + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Deposits money into an account.
     * @param event button click event
     */

    @FXML
    private void handleDeposit(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());
            double amount = Double.parseDouble(this.amountField.getText());

            bankSystem.deposit(id, amount);
            outputArea.appendText("Deposited " + amount + " to account #" + id + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Withdraws money from an account.
     * @param event button click event
     */

    @FXML
    private void handleWithdraw(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());
            double amount = Double.parseDouble(this.amountField.getText());

            bankSystem.withdraw(id, amount);
            outputArea.appendText("Withdrew " + amount + " from account #" + id + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Shows the balance of an account.
     * @param event button click event
     */

    @FXML
    private void handleShowBalance(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());
            double balance = bankSystem.getAccountBalance(id);

            outputArea.appendText("Account #" + id + " balance: " + balance + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Displays the statement of an account.
     * @param event button click event
     */

    @FXML
    private void handleShowStatement(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());
            String statement = bankSystem.getAccountStatement(id);

            outputArea.appendText("Statement for account #" + id + ":\n" + statement + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Transfers money between two accounts.
     * @param event button click event
     */

    @FXML
    private void handleTransfer(ActionEvent event) {
        try {
            int from = Integer.parseInt(accountNumberField.getText());
            int to = Integer.parseInt(targetAccountField.getText());
            double amount = Double.parseDouble(this.amountField.getText());

            bankSystem.transfer(from, to, amount);

            outputArea.appendText("Transferred " + amount + " from #" + from + " to #" + to + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Changes the owner name of an account.
     * @param event button click event
     */

    @FXML
    private void handleChangeOwner(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());
            String newName = newOwnerNameField.getText();

            bankSystem.changeOwnerName(id, newName);

            outputArea.appendText("Changed owner of #" + id + " to " + newName + "\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Removes an account from the system.
     * @param event button click event
     */

    @FXML
    private void handleRemoveAccount(ActionEvent event) {
        try {
            int id = Integer.parseInt(accountNumberField.getText());

            boolean removed = bankSystem.removeAccount(id);

            if (removed)
                outputArea.appendText("Removed account #" + id + "\n");
            else
                outputArea.appendText("Account #" + id + " not found.\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Displays all accounts.
     * @param event button click event
     */

    @FXML
    private void handleShowAllAccounts(ActionEvent event) {
        var list = bankSystem.getAllAccounts();
        outputArea.appendText("==== All Accounts ====\n");
        for (var acc : list) {
            outputArea.appendText(acc.toString() + "\n");
        }
    }

    /**
     * Clears the output area.
     * @param event button click event
     */

    @FXML
    private void handleClearOutput(ActionEvent event) {
        outputArea.clear();
    }
}
