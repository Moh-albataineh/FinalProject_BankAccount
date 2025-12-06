package General;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class BankGUIController {

    // Create Account
    @FXML private TextField ownerNameField;
    @FXML private TextField initialBalanceField;

    // Operations
    @FXML private TextField accountNumberField;
    @FXML private TextField amountField;

    // Transfer
    @FXML private TextField targetAccountField;

    // Manage
    @FXML private TextField newOwnerNameField;

    // Output
    @FXML private TextArea outputArea;

    private BankSystem bankSystem = new BankSystem();

    @FXML
    private void initialize() {
        outputArea.appendText("Welcome to the Bank System!\n");
    }

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

    @FXML
    private void handleShowAllAccounts(ActionEvent event) {
        var list = bankSystem.getAllAccounts();
        outputArea.appendText("==== All Accounts ====\n");
        for (var acc : list)
            outputArea.appendText(acc.toString() + "\n");
    }

    @FXML
    private void handleClearOutput(ActionEvent event) {
        outputArea.clear();
    }
}
