package callie.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A dialog box consisting of an ImageView to represent the speaker and a label containing text.
 */
public class DialogBox extends HBox {
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label dialog;

    private DialogBox(String text, Image img) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this); // to set root again, you need to define the root explicitly in the fxml
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load DialogBox.fxml", e);
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Returns a dialog box for user messages.
     *
     * @param text The user message.
     * @param img  The user display image.
     * @return The user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box for bot messages.
     *
     * @param text The bot message.
     * @param img  The bot display image.
     * @return The bot dialog box.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
