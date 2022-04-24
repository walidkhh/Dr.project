package clinic;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ReservationController {

    @FXML
    private TextField reservationNumber;

    @FXML
    private TextField pName;

    @FXML
    private TextField gender;

    @FXML
    private TextField age;

    @FXML
    private TextField phoneNumber;

    @FXML
    private DatePicker reservationDate;

    @FXML
    private ComboBox<?> reservationType;

    @FXML
    void reservation(ActionEvent event) throws IOException {

    }

    @FXML
    void showAllReservations(ActionEvent event) throws IOException {
        MainView.setRoot("allReservations", 950, 760);
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }
}
