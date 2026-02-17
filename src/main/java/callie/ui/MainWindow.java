package callie.ui;

import callie.logic.Logic;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI window.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    private Logic logic;

    /**
     * Initializes the main window after FXML loading.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setDefaultButton(true);
        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets the logic and displays the greeting message.
     *
     * @param logic The logic handler.
     */
    public void setLogic(Logic logic) {
        this.logic = logic;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(logic.getWelcomeMessage(), botImage));
    }

    /**
     * Handles user input from the text field or send button.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }
        String response = logic.getResponse(input);
        DialogBox botDialog = isListResponse(response)
                ? DialogBox.getListDialog(response, botImage)
                : DialogBox.getBotDialog(response, botImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                botDialog
        );
        userInput.clear();
        if (logic.isExitCommand()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            pauseThenClose();
        }
    }

    /**
     * Allows Callie's farewell message to be seen before the GUI is shut down.
     */
    private void pauseThenClose() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        });
        pause.play();
    }

    private boolean isListResponse(String response) {
        return response.startsWith(" Here are your current tasks in a list:")
                || response.startsWith(" Here are the matching tasks in your list:");
    }
}
