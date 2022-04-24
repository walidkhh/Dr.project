package clinic;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ChosseController {

    @FXML
    void addPatients(ActionEvent event) throws IOException {
        MainView.setRoot("addPatient", 950, 760);
    }

    @FXML
    void allPatients(ActionEvent event) throws IOException {
        MainView.setRoot("allPatients", 950, 760);
    }

    @FXML
    void reservation(ActionEvent event) throws IOException {
        MainView.setRoot("reservation", 950, 760);
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("login",900,770);
    }
}
