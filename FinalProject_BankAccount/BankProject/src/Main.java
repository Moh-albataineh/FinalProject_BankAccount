    /**
     * @author Mohammad Albatainah
     * @version 2025-12-7
     * 
     * The Main class serves as the entry point of the Bank Management System application.
     * This class initializes JavaFX, loads the GUI, and displays the primary stage
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML file cannot be loaded or is missing
     */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Starts the JavaFX application by loading the GUI layout from the FXML file.
     *
     * @param stage the primary stage of the JavaFX application
     * @throws Exception if the FXML file fails to load
     */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/BankGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Bank Management System");
        stage.setScene(scene);

        stage.show();
    }
    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
