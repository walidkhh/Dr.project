package clinic;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminpagesController {

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<?> privileges;

    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void deleteButton(ActionEvent event) {

    }

    @FXML
    void editbutton(ActionEvent event) {

    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

}
