package callie;

import java.io.IOException;

import callie.logic.Logic;
import callie.storage.Storage;
import callie.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX application entry point.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            Parent root = loader.load();
            MainWindow controller = loader.getController();
            controller.setLogic(new Logic(new Storage("./data/callie.txt")));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
            stage.setTitle("Callie");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load MainWindow.fxml", e);
        }
    }
}
